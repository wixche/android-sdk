package com.qiniu.android.http.networkStatus;

import com.qiniu.android.http.request.IUploadServer;
import com.qiniu.android.utils.Utils;

public class UploadServerNetworkStatus {

    public static IUploadServer getBetterNetworkServer(IUploadServer serverA, IUploadServer serverB) {
        return isServerNetworkBetter(serverA, serverB) ? serverA : serverB;
    }

    public static boolean isServerNetworkBetter(IUploadServer serverA, IUploadServer serverB) {
        if (serverA == null) {
            return false;
        } else if (serverB == null) {
            return true;
        }

        String serverTypeA = Utils.getIpType(serverA.getIp(), serverA.getHost());
        String serverTypeB = Utils.getIpType(serverB.getIp(), serverB.getHost());
        if (serverTypeA == null) {
            return false;
        } else if (serverTypeB == null) {
            return true;
        }

        NetworkStatusManager.NetworkStatus serverStatusA = NetworkStatusManager.getInstance().getNetworkStatus(serverTypeA);
        NetworkStatusManager.NetworkStatus serverStatusB = NetworkStatusManager.getInstance().getNetworkStatus(serverTypeB);

        return serverStatusB.getSpeed() < serverStatusA.getSpeed();
    }
}
