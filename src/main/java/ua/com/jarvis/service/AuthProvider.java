package ua.com.jarvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.jarvis.domain.AuthUserDetail;
import ua.com.jarvis.service.security.JpaUserDetailsService;
import java.util.Collection;
import java.util.Objects;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AuthUserDetail user = (AuthUserDetail) userService.loadUserByUsername(username);

        if(Objects.nonNull(user) && (user.getUsername().equals(username)))
        {
            if(!passwordEncoder.matches(password, user.getPassword()))
            {
                throw new BadCredentialsException("Wrong password or username.");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        else
            throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }

}
