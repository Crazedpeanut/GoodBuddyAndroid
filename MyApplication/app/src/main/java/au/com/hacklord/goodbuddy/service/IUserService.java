package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.RegistrationRequestDto;
import au.com.hacklord.goodbuddy.dto.RegistrationResponseDto;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public interface IUserService {
    @POST("user")
    Observable<Response<RegistrationResponseDto>> registerUser(@Body RegistrationRequestDto registrationRequestDto);
}
