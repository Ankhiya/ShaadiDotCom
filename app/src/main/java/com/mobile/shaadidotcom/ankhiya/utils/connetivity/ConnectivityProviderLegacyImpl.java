package com.mobile.shaadidotcom.ankhiya.utils.connetivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mobile.shaadidotcom.ankhiya.utils.connetivity.base.ConnectivityProviderBaseImpl;

/**
 * Connectivity Provider for SDK below N
 */
@SuppressWarnings("DEPRECATION")
public class ConnectivityProviderLegacyImpl extends ConnectivityProviderBaseImpl {

    private final Context context;
    private final ConnectivityReceiver connectivityReceiver;

    public ConnectivityProviderLegacyImpl(Context context, ConnectivityManager connectivityManager) {
        super(connectivityManager);
        this.context = context;
        this.connectivityReceiver = new ConnectivityReceiver();
    }

    @Override
    public void unsubscribe() {
        context.unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void subscribe() {
        context.registerReceiver(connectivityReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public boolean isConnected() {
        boolean connected = false;
        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnectedOrConnecting();
        }
        return connected;
    }

    private class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean connected = false;
            NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null) {
                connected = networkInfo.isConnectedOrConnecting();
            }
            dispatchChange(connected);
        }
    }
}
