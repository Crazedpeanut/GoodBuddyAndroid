package au.com.hacklord.goodbuddy.service;

import android.content.Context;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.concurrent.TimeUnit;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.dto.AuthenticationResponseDto;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by john on 27/08/2016.
 */
public class AuthServiceHelper {

    private static final String BASE_URL = "http://52.63.156.180:8080/";
    private Retrofit retrofit;
    private IAuthService authService;

    public AuthServiceHelper()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        authService = retrofit.create(IAuthService.class);
    }

    public Observable<AuthenticationResponseDto> attemptAuth(String username, String password)
    {
        AuthenticationRequestDto dto = new AuthenticationRequestDto();
        dto.setUsername(username);
        dto.setPassword(password);

        return attemptAuth(dto);
    }

    public Observable<AuthenticationResponseDto> attemptAuth(final AuthenticationRequestDto authenticationRequestDto)
    {
        return authService.authenticateUser(authenticationRequestDto)
                .map(new Func1<Response<AuthenticationResponseDto>, AuthenticationResponseDto>() {
                    @Override
                    public AuthenticationResponseDto call(Response<AuthenticationResponseDto> authenticationResponseDtoResponse) {
                        return authenticationResponseDtoResponse.body();
                    }
                });
    }
}
