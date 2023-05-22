package com.example.helpdev.config;

import com.example.helpdev.security.JWTAuthenticationFilter;
import com.example.helpdev.security.JWTUtil;
import com.example.helpdev.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String PUBLIC_MATCHERS[] = {"/login/**","/tickets","/technician"};

    @Autowired
    private Environment env;

    @Autowired
    private  JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers(headers -> {
                headers.frameOptions(frameOptions -> frameOptions.disable());
            });
        }
         http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                 .addFilter(new JWTAuthenticationFilter(authConfiguration.getAuthenticationManager(), jwtUtil))
                .authorizeHttpRequests(
                        authorizeConfig ->{
                            authorizeConfig.requestMatchers(PUBLIC_MATCHERS).permitAll();
                            authorizeConfig.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        });
         return  http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.POST.name(),
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
