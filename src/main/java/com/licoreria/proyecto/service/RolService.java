package com.licoreria.proyecto.service;

import com.licoreria.proyecto.model.bd.Rol;
import com.licoreria.proyecto.repository.ColaboradorRepository;
import com.licoreria.proyecto.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class RolService implements IRolService{
    private RolRepository rolRepository;
    private ColaboradorRepository colaboradorRepository;

    @Override
    public Rol obtenerRolxNomrol(String nomrol) {
        return rolRepository.findByNomrol(nomrol);
    }

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> listarRol() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerRolxId(Integer idrol) {
        return rolRepository.findById(idrol).orElse(null);
    }

    @Override
    public void actualizarRol(Rol rol) {
        rolRepository.actualizarRoles(
                rol.getNomrol(),
                rol.getIdrol()
        );

    }
}
