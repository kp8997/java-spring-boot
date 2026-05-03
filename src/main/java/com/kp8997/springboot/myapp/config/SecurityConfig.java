package com.kp8997.springboot.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    // spring security won't use the user/password configured in properties and will use this instead
    //@Bean
    //public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    //    UserDetails join = User.builder().username("john")
    //            .password("{noop}test123")
    //            .roles("EMPLOYEE")
    //            .build();
    //
    //    UserDetails marry = User.builder().username("marry")
    //            .password("{noop}test123")
    //            .roles("EMPLOYEE", "MANAGER")
    //            .build();
    //
    //    UserDetails tim = User.builder().username("tim")
    //            .password("{noop}test123")
    //            .roles("EMPLOYEE", "MANAGER", "ADMIN")
    //            .build();
    //
    //    return new InMemoryUserDetailsManager(join, marry, tim);
    //}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
                        http.httpBasic(Customizer.withDefaults());
                        http.csrf(AbstractHttpConfigurer::disable);

                        System.out.println("filter chain");
        return http.build();
    }

    // custom bean with jdbc instead of chain above
    // comment because i customized another bean in MyappApplication
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        System.out.println("data source" + dataSource.toString());

        return new JdbcUserDetailsManager(dataSource);
    }
}
