package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.model.AuthenticationResponse;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public class AuthServiceHelper {

    private static final String BASE_URL = "http://localhost:8080/";
    private Retrofit retrofit;
    private IAuthService authService;

    public AuthServiceHelper()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        IAuthService authService = retrofit.create(IAuthService.class);
    }

    public Observable<AuthenticationResponse> attemptAuth(String username, String password)
    {
        AuthenticationRequestDto dto = new AuthenticationRequestDto();
        dto.setUsername(username);
        dto.setPassword(password);

        return attemptAuth(dto);
    }

    public Observable<AuthenticationResponse> attemptAuth(AuthenticationRequestDto authenticationRequestDto)
    {
        return authService.authenticateUser(authenticationRequestDto);
    }
}
