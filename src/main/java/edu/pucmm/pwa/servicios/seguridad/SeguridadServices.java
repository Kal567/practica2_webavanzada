package edu.pucmm.pwa.servicios.seguridad;

import edu.pucmm.pwa.entidades.seguridad.Mock;
import edu.pucmm.pwa.entidades.seguridad.Rol;
import edu.pucmm.pwa.entidades.seguridad.Usuario;
import edu.pucmm.pwa.repositorio.seguridad.RolRepository;
import edu.pucmm.pwa.repositorio.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SeguridadServices implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    //Para encriptar la información.
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    //cualquier cosa...

    /**
     * Creando el usuario por defecto y su rol.
     */
    public void crearUsuarioAdmin(){
        System.out.println("Creación del usuario y rol en la base de datos");
        Rol rolAdmin = new Rol("ROLE_ADMIN");
        rolRepository.save(rolAdmin);

        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setNombre("Administrador");
        admin.setActivo(true);
        admin.setRoles(new HashSet<>(Arrays.asList(rolAdmin)));
        usuarioRepository.save(admin);

        Rol rolUser = new Rol("ROLE_USER");
        rolRepository.save(rolUser);
    }

   /* public void savenewUser(String name, String username, String password){
        Rol rolUser = new Rol("ROLE_USER");

        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setNombre(name);
        user.isAdmin(false);
        user.setRoles(new HashSet<>(Arrays.asList(rolUser)));
        usuarioRepository.save(user);
    }*/




    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        Cookie cookie = new Cookie("user", username);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActivo(), true, true, true, grantedAuthorities);
    }
}
