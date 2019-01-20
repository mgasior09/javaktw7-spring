package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("Ecik").password("ecik123").roles("user", "add").build());
        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("Adelajda").password("lampka").roles("user", "sell", "purchases").build());
        return userDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/vehicles/add").hasRole("add")
                .antMatchers("/vehicles/**/sell").hasRole("sell")
                .antMatchers(HttpMethod.POST,"/vehicles").hasRole("sell")
                .antMatchers("/purchases/**").hasRole("purchases")
                .anyRequest().permitAll()
                .and().formLogin().defaultSuccessUrl("/vehicles")
                .and().logout().logoutSuccessUrl("/vehicles")
                .and().httpBasic();
    }
}
