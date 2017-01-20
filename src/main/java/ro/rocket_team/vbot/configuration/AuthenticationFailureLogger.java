package ro.rocket_team.vbot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureLogger implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFailureLogger.class);

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent event) {
        final Object userName = event.getAuthentication().getPrincipal();
        final Object credentials = event.getAuthentication().getCredentials();
        LOG.error("Failed login using USERNAME [" + userName + "] and password [" + credentials + "]");
    }
}