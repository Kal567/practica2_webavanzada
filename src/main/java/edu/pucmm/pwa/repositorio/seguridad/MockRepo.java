package edu.pucmm.pwa.repositorio.seguridad;


import edu.pucmm.pwa.entidades.seguridad.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockRepo extends JpaRepository<Mock, Integer> {

    List findAllByOwnerName(String username);

    Mock findByIdMock(int idMock);

}
