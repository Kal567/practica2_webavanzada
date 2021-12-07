package edu.pucmm.pwa.repositorio.seguridad;

import edu.pucmm.pwa.entidades.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    //Cualquier metodo que necesite incluir.
    Usuario findByUsername(String username);

}
