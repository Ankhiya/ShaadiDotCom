package com.mobile.shaadidotcom.ankhiya.utils.connetivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.mobile.shaadidotcom.ankhiya.utils.connetivity.base.ConnectivityProviderBaseImpl;

/**
 * Connectivity provider for SDK above or equal to N
 */
@RequiresApi(Build.VERSION_CODES.N)
public class ConnectivityProviderImpl extends ConnectivityProviderBaseImpl {

    private final ConnectivityCallback mNetworkCallback;

    public ConnectivityProviderImpl(final ConnectivityManager connectivityManager) {
        super(connectivityManager);
        mNetworkCallback = new ConnectivityCallback();
    }

    @Override
    protected void subscribe() {
        mManager.registerDefaultNetworkCallback(mNetworkCallback);
    }

    @Override
    protected void unsubscribe() {
        mManager.unregisterNetworkCallback(mNetworkCallback);
    }

    @Override
    public boolean isConnected() {
        boolean connected = false;
        NetworkCapabilities capabilities = mManager.getNetworkCapabilities(mManager.getActiveNetwork());
        if (capabilities != null) {
            connected = hasCapability(capabilities);
        }
        return connected;
    }

    private boolean hasCapability(@NonNull NetworkCapabilities capabilities) {
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }

    class ConnectivityCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onCapabilitiesChanged(@NonNull Network network,
                                          @NonNull NetworkCapabilities networkCapabilities) {
            dispatchChange(hasCapability(networkCapabilities));
        }

        @Override
        public void onLost(@NonNull Network network) {
            dispatchChange(false);
        }
    }
}
