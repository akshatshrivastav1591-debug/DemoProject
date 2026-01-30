package com.example.React_Spring.Security.SecurityConfig;


import com.example.React_Spring.Security.Jwt.Filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
UserDetailsService Owner; //--> Instance object of UserDetailService interface whose implementation is our SecurityService CLass
@Autowired
JwtFilter jwtfilter; //--> The object of jwt filter class

@Bean
public AuthenticationProvider authProvider(){ //--> Telling security that we are defining our Authentication provider:
    DaoAuthenticationProvider Provider=new DaoAuthenticationProvider(Owner);//-->We are Using Dao Authentication provider
    //is accepting the Instance Object of UserDetailsService Interface as a Constructor value:whose implementation class is our Security Service class
    Provider.setPasswordEncoder(new BCryptPasswordEncoder(12));//->setting the password encoder to hash  our password by using the Object of Dao authenticatiob Provider CLass:
    //Hashing-->Hashing is the one encryption in which once password is encoded it can't be decoded,Password matching occur in the following way:
    // The entered password is converted  to the hashed  code and then matched to the password stored in the database:
    // if the password is matched the request is proccesed further or user is not allowed to login:
    System.out.println("Authenticatiob Provider is Working:");
    return Provider; //-> returning the object of Authentication Provider:
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configur){
    return  configur.getAuthenticationManager();
}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
    http.csrf(customizer->customizer.disable());//-->Disabling Csrf Token
        http.cors(cors->{});//-->Enables CORS handling so frontend (React) can call backend APIs.
        http.authorizeHttpRequests(request->request.requestMatchers("/register","/login")
                .permitAll().anyRequest().authenticated());//--> Telling the Security to authenticate every request send to the server with the exception of ("/register","/login")
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);//-->Making Request a Stateless  and adding jwt filer;
        http.httpBasic(Customizer.withDefaults());//-->Enables Basic Auth (mainly useful for testing or Postman). In pure JWT-based APIs, this can be removed.

        System.out.println("Security chain method is Working:");
return http.build();//-->returning the SecurityFilterChain is an interface, \
// and Spring Security uses DefaultSecurityFilterChain as its concrete implementation, created via HttpSecurity.build().
    }
}
