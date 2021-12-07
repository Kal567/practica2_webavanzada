package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.entidades.seguridad.Usuario;
import edu.pucmm.pwa.repositorio.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by vacax on 27/09/16.
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error, Model model, Locale locale) {
        model.addAttribute("usuario", messageSource.getMessage("usuario", null, locale));
        model.addAttribute("contrasena", messageSource.getMessage("contrasena", null, locale));
        model.addAttribute("iniciarsesion", messageSource.getMessage("iniciarsesion", null, locale));
        model.addAttribute("crearnuevousuario", messageSource.getMessage("crearnuevousuario", null, locale));

        return new ModelAndView("login", "error", error);
    }

}
