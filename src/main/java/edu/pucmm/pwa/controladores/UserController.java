package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.entidades.seguridad.Mock;
import edu.pucmm.pwa.entidades.seguridad.Rol;
import edu.pucmm.pwa.entidades.seguridad.Usuario;
import edu.pucmm.pwa.repositorio.seguridad.MockRepo;
import edu.pucmm.pwa.repositorio.seguridad.RolRepository;
import edu.pucmm.pwa.repositorio.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    MockRepo mockRepo;

    @Autowired
    private MessageSource messageSource;

    //Para encriptar la información.
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //@Autowired
    //private SeguridadServices seguridadServices;

    @GetMapping("/signup")
    public String signup(){
        return "signUp";
    }

    @PostMapping("/newuser")
    public String newUser(@RequestParam("name") String name,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Locale locale,
                          Model model){
        Rol role = rolRepository.findByRole("ROLE_USER");

        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setNombre(name);
        user.setActivo(true);
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        usuarioRepository.save(user);

        //SeguridadServices seguridadServices = (SeguridadServices) applicationContext.getBean("seguridadServices");
        //seguridadServices.savenewUser(name, username, password);
        model.addAttribute("usuario", messageSource.getMessage("usuario", null, locale));
        model.addAttribute("contrasena", messageSource.getMessage("contrasena", null, locale));
        model.addAttribute("iniciarsesion", messageSource.getMessage("iniciarsesion", null, locale));
        model.addAttribute("crearnuevousuario", messageSource.getMessage("crearnuevousuario", null, locale));


        return "login";
    }

    @GetMapping("/mymocks")
    public String mymockups(@CookieValue("user") String username, Model model, Locale locale){
        //Usuario user = usuarioRepository.findByUsername(username);
        model.addAttribute("mocks", mockRepo.findAllByOwnerName(username));

        model.addAttribute("crearmock", messageSource.getMessage("crearmock", null, locale));
        model.addAttribute("mismocks", messageSource.getMessage("mismocks", null, locale));
        model.addAttribute("todoslosmocks", messageSource.getMessage("todoslosmocks", null, locale));
        model.addAttribute("usuarioss", messageSource.getMessage("usuarioss", null, locale));
        model.addAttribute("cerrarsesion", messageSource.getMessage("cerrarsesion", null, locale));
        model.addAttribute("usuario", messageSource.getMessage("usuario", null, locale));
        model.addAttribute("nombre", messageSource.getMessage("nombre", null, locale));
        model.addAttribute("ver", messageSource.getMessage("ver", null, locale));
        model.addAttribute("eliminar", messageSource.getMessage("eliminar", null, locale));

        return "myMockups";
    }

    @PostMapping("/deleteMyMock/{idMock}")
    public String deleteMyMock(@PathVariable int idMock){
        Mock mocky = mockRepo.findByIdMock(idMock);
        mockRepo.delete(mocky);
        return "redirect:/mymocks";
    }

    @PostMapping("/deleteMock/{idMock}")
    public String deleteMock(@PathVariable int idMock){
        Mock mocky = mockRepo.findByIdMock(idMock);
        mockRepo.delete(mocky);
        return "redirect:/allmockys";
    }

    @PostMapping("/edituser")
    public String edituser(@RequestParam("username") String username,
                           @RequestParam(value = "admin", required = false) boolean admin,
                           Model model,
                           Locale locale){
        Usuario user = usuarioRepository.findByUsername(username);
        Rol adminRol = rolRepository.findByRole("ROLE_ADMIN");
        Rol userRol = rolRepository.findByRole("ROLE_USER");
        Set<Rol> userroles =  user.getRoles();
        boolean isadminalready = false;
        for (Rol rol : user.getRoles()){
            if (rol.getRole() == "ROLE_ADMIN"){
                isadminalready = true;
            }
        }
        if (admin && !isadminalready){
            userroles.add(adminRol);
            user.setRoles(userroles);
        }
        if (!admin && isadminalready){
            user.setRoles(new HashSet<>(Arrays.asList(userRol)));
        }

        return "redirect:/allusers";
    }

    @PostMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username){
        Usuario user = usuarioRepository.findByUsername(username);
        usuarioRepository.delete(user);
        return "redirect:/allusers";
    }

    /*@GetMapping("/adminform")
    public String adminform(){
        return "adminForm";
    }*/

    /*@GetMapping("/logout")
    public String logout(@CookieValue("user") String username,
                         HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("user", username);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "login";
    }*/

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/allmockys")
    public String allmockys(Model model, Locale locale){
        //Usuario user = usuarioRepository.findByUsername(username);
        model.addAttribute("mocks", mockRepo.findAll());

        model.addAttribute("crearmock", messageSource.getMessage("crearmock", null, locale));
        model.addAttribute("mismocks", messageSource.getMessage("mismocks", null, locale));
        model.addAttribute("todoslosmocks", messageSource.getMessage("todoslosmocks", null, locale));
        model.addAttribute("usuarioss", messageSource.getMessage("usuarioss", null, locale));
        model.addAttribute("cerrarsesion", messageSource.getMessage("cerrarsesion", null, locale));
        model.addAttribute("usuario", messageSource.getMessage("usuario", null, locale));
        model.addAttribute("nombre", messageSource.getMessage("nombre", null, locale));
        model.addAttribute("ver", messageSource.getMessage("ver", null, locale));
        model.addAttribute("eliminar", messageSource.getMessage("eliminar", null, locale));

        return "adminForm"; //para ver todos los mocks
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/allusers")
    public String allusers(@CookieValue("user") String username, Model model, Locale locale){
        //Usuario user = usuarioRepository.findByUsername(username);
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<String> roles = new ArrayList<String>();
        String rolelist = "";
        for (Usuario user: usuarios){
            for (Rol rol: user.getRoles()){
                rolelist = rolelist + "." + rol.getRole();
            }
            roles.add(rolelist);
            rolelist = "";
        }
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);

        model.addAttribute("crearmock", messageSource.getMessage("crearmock", null, locale));
        model.addAttribute("mismocks", messageSource.getMessage("mismocks", null, locale));
        model.addAttribute("todoslosmocks", messageSource.getMessage("todoslosmocks", null, locale));
        model.addAttribute("usuarioss", messageSource.getMessage("usuarioss", null, locale));
        model.addAttribute("cerrarsesion", messageSource.getMessage("cerrarsesion", null, locale));
        model.addAttribute("username", messageSource.getMessage("username", null, locale));
        model.addAttribute("roless", messageSource.getMessage("roless", null, locale));
        model.addAttribute("editarusuario", messageSource.getMessage("editarusuario", null, locale));
        model.addAttribute("haceradmin", messageSource.getMessage("haceradmin", null, locale));
        model.addAttribute("editar", messageSource.getMessage("editar", null, locale));
        model.addAttribute("eliminar", messageSource.getMessage("eliminar", null, locale));


        return "userForm"; //para ver todos los usuarios
    }
}
