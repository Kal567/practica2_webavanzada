package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.entidades.seguridad.Mock;
import edu.pucmm.pwa.entidades.seguridad.Usuario;
import edu.pucmm.pwa.repositorio.seguridad.MockRepo;
import edu.pucmm.pwa.repositorio.seguridad.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpResponse;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MockRepo mockRepo;

    @Value("${token_jwt}")
    private String llaveSecreta;

    @PostMapping("/auth")
    public RedirectView auth(@RequestParam("rutaEndpoint") String rutaEndpoint,
                       @RequestParam("descripcion") String descripcion,
                       @RequestParam("key") String key,
                       @RequestParam("metodoAcceso") String metodoAcceso,
                       @RequestParam("headers") String headers,
                       @RequestParam("codigoRespuesta") String codigoRespuesta,
                       @RequestParam("contentType") String contentType,
                       @RequestParam("delay") String delay,
                       @RequestParam("response") String response,
                       @RequestParam(value = "jwt", required = false) boolean jwt,
                       @RequestParam("tiempoExpiracion") String tiempoExpiracion,
                       @CookieValue("user") String username,
                       Model model){
        //Usuario user = usuarioRepository.findByUsername(username);
        System.out.println(username);
        String token = "";
        if (jwt){
            Usuario usuario = usuarioRepository.findByUsername(username);
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            if(usuario==null && !usuario.getPassword().equals(bCryptPasswordEncoder.encode(usuario.getPassword()))){
                return new RedirectView("/");
                //return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            token = generarToken(usuario);
        }

        Mock mocky = new Mock();
        mocky.setOwnerName(username);
        mocky.setMockName(rutaEndpoint);
        mocky.setAccessMethod(metodoAcceso);
        mocky.setHeader(headers);
        mocky.setHeaderKey(key);
        mocky.setContentType(contentType);
        mocky.setResponseBody(response);
        mocky.setDescription(descripcion);
        mocky.setResponseCode(codigoRespuesta);
        mocky.setRespondeDelay(Integer.parseInt(delay));
        int tiempoexp = 0;
        if (tiempoExpiracion == "1 Mes"){
            tiempoexp = 2628000;
        }
        if (tiempoExpiracion == "1 Semana"){
            tiempoexp = 604800;
        }
        if (tiempoExpiracion == "1 día"){
            tiempoexp = 86400;
        }
        if (tiempoExpiracion == "1 Hora"){
            tiempoexp = 60;
        }
        mocky.setExpireTime(tiempoexp);
        mocky.setToken(token);
        mockRepo.save(mocky);

        return new RedirectView("/mymocks");
        //return new ResponseEntity<>(token, HttpStatus.OK);// "redirect:/token"
    }

    @PostMapping("/getMock/{idMock}")
    public ResponseEntity<String> getMock(@PathVariable int idMock, @RequestHeader HttpHeaders httpHeaders){
        Mock mocky = mockRepo.findByIdMock(idMock);
        Map<String, String> mapHeaders = httpHeaders.toSingleValueMap();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MockId",String.valueOf(mocky.getIdMock()));
        responseHeaders.set("MockId",String.valueOf(mocky.getIdMock()));
        responseHeaders.set("Username",mocky.getOwnerName());
        responseHeaders.set("Access-Method",mocky.getAccessMethod());
        responseHeaders.set("Header-Key",mocky.getHeaderKey());
        responseHeaders.set("Header-Value",mocky.getHeader());
        responseHeaders.set("Content-Type",mocky.getContentType());

        mapHeaders.put("Response Body",mocky.getResponseBody());
        JSONObject jsonObject= new JSONObject(mapHeaders);
        responseHeaders.put("Response-Body", Collections.singletonList(jsonObject.toString()));

        responseHeaders.set("Description",mocky.getDescription());
        responseHeaders.set("Response-Code", mocky.getResponseCode());
        responseHeaders.set("Response-Delay",String.valueOf(mocky.getRespondeDelay()));
        responseHeaders.set("Expire-Time", String.valueOf(mocky.getExpireTime()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(mocky.getResponseBody());
    }

    @RequestMapping("/")
    public String index(){
       return "Hola Mundo con JWT";
    }

    /**
     *
     * @param usuario
     * @return
     */
    private String generarToken(Usuario usuario) {

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(usuario.getNombre())
                .claim("roles",usuario.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        llaveSecreta.getBytes()).compact();

        //return "Bearer " + token;
        return token;
    }
}
