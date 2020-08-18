package com.mobile.shaadidotcom.ankhiya.repository.network;

import com.mobile.shaadidotcom.ankhiya.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client to handle network calls
 * */
public final class RetrofitClient {

    /* static instance of retrofit */
    private static Retrofit sInstance;

    /**
     * Call this method to get instance of {@link Retrofit} for api calls
     * @return retrofit instance {@link Retrofit}
     */
    public static Retrofit getInstance() {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getInstance().create(serviceClass);
    }

    private RetrofitClient() {
    }

}
