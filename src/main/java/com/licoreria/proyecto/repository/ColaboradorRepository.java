package com.licoreria.proyecto.repository;

import com.licoreria.proyecto.model.bd.Colaborador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    Colaborador findByNomusuario(String nomusuario);

    @Transactional
    @Modifying
    @Query(value = "UPDATE colaborador SET nombres =:nombres,"+
                    "apellidos =:apellidos, activo =:activo where "+
                    "idcolaborador =:idcolaborador",nativeQuery = true)
    void actualizarColaborador(@Param("nombres") String nombres,
                               @Param("apellidos") String apellidos,
                               @Param("activo") boolean activo,
                               @Param("idcolaborador") Integer idcolaborador);
}
