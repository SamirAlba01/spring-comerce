package damex.com.damex.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringBootSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests()
                .antMatchers("/administrador/**").hasRole("ADMINISTRADOR")
                .antMatchers("/productos/**").hasRole("ADMINISTRADOR");



                //.and().formLogin().loginPage("/usuario/login")


        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Argon2PasswordEncoder();
    }

}
