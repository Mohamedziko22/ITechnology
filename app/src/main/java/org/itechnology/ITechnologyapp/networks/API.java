package org.itechnology.ITechnologyapp.networks;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.itechnology.ITechnologyapp.models.CategoriesResponseModel;
import org.itechnology.ITechnologyapp.models.UserResponseModel;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "https://balj.roomaclass.com/balj/";

    static Gson gson = new GsonBuilder().setLenient().create();

    private static Retrofit retrofit;
    private static API INSTANCE;
    private static ApiInterface anInterface;


    public API() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(getUnsafeOkHttpClient().build()).build();
        anInterface = retrofit.create(ApiInterface.class);

    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static API getInstance() {
        if (INSTANCE == null) INSTANCE = new API();

        return INSTANCE;
    }

    public Call<UserResponseModel> login(String email, String pass) {
        return anInterface.login(email, pass);
    }

    public Observable<CategoriesResponseModel> getCategory() {
        return anInterface.getCategory();
    }
}
