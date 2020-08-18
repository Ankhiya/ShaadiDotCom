package com.mobile.shaadidotcom.ankhiya.utils.connetivity.base;

import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

/**
 * base implementation
 */
public abstract class ConnectivityProviderBaseImpl implements ConnectivityProvider {

    private boolean subscribed;
    private final List<ConnectivityStateListener> mListener;
    private final Handler mMainLooper;
    protected final ConnectivityManager mManager;

    public ConnectivityProviderBaseImpl(final ConnectivityManager connectivityManager) {
        mListener = new ArrayList<>();
        mMainLooper = new Handler(Looper.getMainLooper());
        mManager = connectivityManager;
    }

    @Override
    public void addListener(ConnectivityStateListener listener) {
        mListener.add(listener);
        listener.onStateChange(isConnected());
        verifySubscription();
    }

    @Override
    public void removeListener(ConnectivityStateListener listener) {
        mListener.remove(listener);
        verifySubscription();
    }

    private void verifySubscription() {
        if (!subscribed && !mListener.isEmpty()) {
            subscribe();
            subscribed = true;
        } else if (subscribed && mListener.isEmpty()) {
            unsubscribe();
            subscribed = false;
        }
    }

    protected void dispatchChange(boolean networkState) {
        mMainLooper.post(() -> {
            for (ConnectivityStateListener listener : mListener) {
                listener.onStateChange(networkState);
            }
        });
    }

    protected abstract void subscribe();

    protected abstract void unsubscribe();
}
