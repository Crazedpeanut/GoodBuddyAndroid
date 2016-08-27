package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.dto.AuthenticationResponseDto;
import au.com.hacklord.goodbuddy.dto.RegistrationRequestDto;
import au.com.hacklord.goodbuddy.dto.RegistrationResponseDto;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by john on 27/08/2016.
 */
public class UserServiceHelper {
    private static final String BASE_URL = "http://10.0.3.2:8080/";
    private Retrofit retrofit;
    private IUserService userService;

    public UserServiceHelper()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
}
