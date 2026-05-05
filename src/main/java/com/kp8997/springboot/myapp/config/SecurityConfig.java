package com.kp8997.springboot.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    //@Bean
    //public UserDetailsManager userDetailsManager(DataSource dataSource) {
    //    System.out.println("data source" + dataSource.toString());
    //
    //    return new JdbcUserDetailsManager(dataSource);
    //}

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

    // custom bean with jdbc instead of chain above
    // comment because i customized another bean in MyappApplication
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

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        // 1. Override the "Create User" query to convert boolean to smallint
        // PostgreSQL doesn't support casting boolean to smallint directly.
        //manager.setCreateUserSql(
        //        "insert into users (username, password, enabled) values (?,?, CASE WHEN ? THEN 1 ELSE 0 END)"
        //);
        //
        //// 2. Override the "Load User" query if necessary
        //// This ensures that when Spring reads the smallint, it treats 1 as true.
        //manager.setUsersByUsernameQuery(
        //        "select username, password, (enabled::int = 1) as enabled from users where username = ?"
        //);

        // 2. Override the "Load User" query if necessary
        // This ensures that when Spring reads the smallint, it treats 1 as true.
        manager.setUsersByUsernameQuery(
                "select user_id, password, (active::int = 1) as enabled from members where user_id = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?"
        );

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
