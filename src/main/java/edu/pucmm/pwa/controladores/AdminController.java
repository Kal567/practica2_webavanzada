package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.repositorio.seguridad.MockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by vacax on 27/09/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/crear")
    public String crear(){
        return "Crear del admin";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/borrar")
    public String borrar(){
        return "Borrar del admin";
    }

    @Secured({"ROLE_USER"})
    @RequestMapping("/actualizar")
    public String actualizar(){
        return "Actualizar del admin";
    }

}
