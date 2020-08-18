package com.mobile.shaadidotcom.ankhiya.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * Network utils
 */
public class NetworkUtils {

    private NetworkUtils() {

    }

    /**
     * Call this method to get network connectivity
     *
     * @param context context to get connectivity service
     * @return true if connected, false otherwise
     */
    public static boolean isConnected(@NonNull Context context) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Network network = cm.getActiveNetwork();
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                if (capabilities != null) {
                    isConnected = capabilities
                            .hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                }
            } else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
            }
        }
        return isConnected;
    }
}
