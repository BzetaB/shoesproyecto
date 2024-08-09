package com.licoreria.proyecto.repository;

import com.licoreria.proyecto.model.bd.Rol;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
    @Transactional
    @Modifying
    @Query(value = "UPDATE rol SET nomrol =:nomrol where idrol =:idrol",nativeQuery = true)
    void actualizarRoles(@Param("nomrol")String nomrol,
                         @Param("idrol")Integer idrol);
}
