package com.mobile.shaadidotcom.ankhiya.utils.connetivity.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.annotation.NonNull;

import com.mobile.shaadidotcom.ankhiya.utils.connetivity.ConnectivityProviderImpl;
import com.mobile.shaadidotcom.ankhiya.utils.connetivity.ConnectivityProviderLegacyImpl;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Connectivity provide interface
 */
public interface ConnectivityProvider {

    interface ConnectivityStateListener {
        void onStateChange(boolean isConnected);
    }

    void addListener(ConnectivityStateListener listener);

    void removeListener(ConnectivityStateListener listener);

    boolean isConnected();

    static ConnectivityProvider createProvider(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new ConnectivityProviderImpl(connectivityManager);
        } else {
            return new ConnectivityProviderLegacyImpl(context, connectivityManager);
        }
    }
}
