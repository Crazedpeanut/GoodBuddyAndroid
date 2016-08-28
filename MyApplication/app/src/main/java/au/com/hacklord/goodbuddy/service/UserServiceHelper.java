package au.com.hacklord.goodbuddy.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.dto.AuthenticationResponseDto;
import au.com.hacklord.goodbuddy.dto.RegistrationRequestDto;
import au.com.hacklord.goodbuddy.dto.RegistrationResponseDto;
import au.com.hacklord.goodbuddy.model.User;
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
public class UserServiceHelper {
    private static final String BASE_URL = "http://52.63.156.180:8080/";
    private Retrofit retrofit;
    private IUserService userService;

    public UserServiceHelper()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        userService = retrofit.create(IUserService.class);
    }

    public Observable<RegistrationResponseDto> registerUser(RegistrationRequestDto registrationRequestDto)
    {
        return userService.registerUser(registrationRequestDto)
                .map(new Func1<Response<RegistrationResponseDto>, RegistrationResponseDto>() {
                    @Override
                    public RegistrationResponseDto call(Response<RegistrationResponseDto> registrationResponseDtoResponse) {
                        return registrationResponseDtoResponse.body();
                    }
                });
    }

    public Observable<RegistrationResponseDto> registerUser(User user)
    {
        RegistrationRequestDto dto = new RegistrationRequestDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFcmId(FirebaseInstanceId.getInstance().getToken());

        return registerUser(dto);
    }
}
