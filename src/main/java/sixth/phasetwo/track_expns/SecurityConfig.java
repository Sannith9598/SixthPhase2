package sixth.phasetwo.track_expns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(customizer -> customizer.disable())  // Disable CSRF for simplicity, enable if needed later
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").authenticated()  // Protect all /api endpoints
                .anyRequest().permitAll()                    // Allow others
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());  

        return http.build();
    }

}
