package ua.com.jarvis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import ua.com.jarvis.service.security.JpaUserDetailsService;

@Configuration
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    private final String BCRYPT_PASSWORD_ENCODE = "{bcrypt}";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // TODO save client in DB

        clients.inMemory()
                .withClient("android")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(jpaUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(bCryptPasswordEncoder);
    }


}
