package com.licoreria.proyecto.service;

import com.licoreria.proyecto.model.bd.Colaborador;
import com.licoreria.proyecto.model.bd.Rol;
import com.licoreria.proyecto.repository.ColaboradorRepository;
import com.licoreria.proyecto.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@AllArgsConstructor
@Service
public class ColaboradorService implements IColaboradorService{
    private ColaboradorRepository colaboradorRepository;
    private RolRepository rolRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public Colaborador obtenerColaboradorxNomusuario(String numusuario) {
        return colaboradorRepository.findByNomusuario(numusuario);
    }

    @Override
    public Colaborador guardarColaborador(Colaborador colaborador) {
        colaborador.setPassword(bCryptPasswordEncoder.encode("123456"));
        colaborador.setActivo(true);
        Rol colaboradorRol = rolRepository.findByNomrol("USER");
        colaborador.setRoles(new HashSet<>(Arrays.asList(colaboradorRol)));
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public List<Colaborador> listarColaborador() {
        return colaboradorRepository.findAll();
    }

    @Override
    public Colaborador obtenerColaboradorxId(Integer id) {
        return colaboradorRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizarColaborador(Colaborador colaborador) {
        colaboradorRepository.actualizarColaborador(
                colaborador.getNombres(),
                colaborador.getApellidos(),
                colaborador.getActivo(),
                colaborador.getIdcolaborador()
        );

    }


}
