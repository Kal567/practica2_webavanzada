package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.entidades.seguridad.Mock;
import edu.pucmm.pwa.entidades.seguridad.Rol;
import edu.pucmm.pwa.entidades.seguridad.Usuario;
import edu.pucmm.pwa.repositorio.seguridad.MockRepo;
import edu.pucmm.pwa.repositorio.seguridad.RolRepository;
import edu.pucmm.pwa.repositorio.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Para encriptar la información.
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //@Autowired
    //private SeguridadServices seguridadServices;

    @GetMapping("/signup")
    public String signup(){
        return "signUp";
    }

    @PostMapping("/newuser")
    public String newUser(@RequestParam("name") String name, @RequestParam("username") String username, @RequestParam("password") String password){
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
        return "login";
    }

    @GetMapping("/mymocks")
    public String mymockups(@CookieValue("user") String username, Model model){
        //Usuario user = usuarioRepository.findByUsername(username);
        model.addAttribute("mocks", mockRepo.findAllByOwnerName(username));
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
                           @RequestParam(value = "admin", required = false) boolean admin){
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
        return "userForm";
    }

    @PostMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username){
        Usuario user = usuarioRepository.findByUsername(username);
        usuarioRepository.delete(user);
        return "redirect:/allusers";
    }

    @GetMapping("/adminform")
    public String adminform(){
        return "adminForm";
    }

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
    public String allmockys(Model model){
        //Usuario user = usuarioRepository.findByUsername(username);
        model.addAttribute("mocks", mockRepo.findAll());
        return "adminForm"; //para ver todos los mocks
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/allusers")
    public String allusers(@CookieValue("user") String username, Model model){
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
        return "userForm"; //para ver todos los usuarios
    }
}
