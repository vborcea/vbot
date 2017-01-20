package ro.rocket_team.vbot.configuration;


import org.junit.Test;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static ro.rocket_team.vbot.configuration.CustomAuthenticationSuccessHandler.INDEX_HTML;

public class CustomAuthenticationSuccessHandlerTest {

    @Test
    public void redirectedOKAfterLogin() throws IOException, ServletException {
        final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final Authentication authentication = mock(Authentication.class);
        customAuthenticationSuccessHandler.onAuthenticationSuccess(mock(HttpServletRequest.class), response, authentication);

        verify(response,times(1)).sendRedirect(INDEX_HTML);
    }
}
