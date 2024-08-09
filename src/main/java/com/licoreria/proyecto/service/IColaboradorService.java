package com.licoreria.proyecto.service;

import com.licoreria.proyecto.model.bd.Colaborador;

import java.util.List;

public interface IColaboradorService {
    Colaborador obtenerColaboradorxNomusuario(String numusuario);
    Colaborador guardarColaborador(Colaborador colaborador);
    List<Colaborador> listarColaborador();
    Colaborador obtenerColaboradorxId(Integer id);
    void actualizarColaborador(Colaborador colaborador);
}
