package edu.pucmm.pwa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by vacax on 27/09/16.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    //Configuación para la validación del acceso modo JDBC
    @Autowired
    private DataSource dataSource;
    @Value("${query.user-jdbc}")
    private String queryUsuario;
    @Value("${query.rol-jdbc}")
    private String queryRol;
    //Opción JPA
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * La autentificación de los usuarios.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Clase para encriptar contraseña
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        //Configuración JPA.
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http
                .authorizeRequests()
                .antMatchers("/","/css/**", "/js/**", "/actuator/**", "/webjars/**").permitAll() //permitiendo llamadas a esas urls.
                .antMatchers("/dbconsole/**").permitAll()
                .antMatchers("/thymeleaf/**", "/freemarker/**", "/api/**", "/jpa/**").permitAll()
                .antMatchers("/api-docs/**", "/api-docs.yaml", "/swagger-ui.html", "/swagger-ui/**").permitAll() //para OpenApi
                .antMatchers("/admin/", "/allmockys").hasAnyRole("ADMIN")
                .antMatchers("/mymocks").hasAnyRole("ADMIN", "USER")
                .antMatchers("/signup", "/newuser", "/api/getMock/**").permitAll() //hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated() //cualquier llamada debe ser validada
                .and()
                .formLogin()
                .loginPage("/login").permitAll() //indicando la ruta que estaremos utilizando.
                //.failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina.
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //TODO: validar exclusivamente en ambiente de prueba.
        // deshabilitando las seguridad contra los frame internos.
        //if(!profiles.matches(Pre"prod")){
            //Necesario para H2.
            http.csrf().disable();
            http.headers().frameOptions().disable();
        //}
    }
}
