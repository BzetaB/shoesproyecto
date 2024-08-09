package com.licoreria.proyecto.service;

import com.licoreria.proyecto.model.bd.Rol;

import java.util.List;

public interface IRolService {
    Rol obtenerRolxNomrol(String nomrol);
    Rol guardarRol(Rol rol);
    List<Rol>listarRol();
    Rol obtenerRolxId(Integer idrol);
    void actualizarRol(Rol rol);
}
