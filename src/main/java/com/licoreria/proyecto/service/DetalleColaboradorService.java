package com.licoreria.proyecto.service;

import com.licoreria.proyecto.model.bd.Colaborador;
import com.licoreria.proyecto.model.bd.Rol;
import com.licoreria.proyecto.model.dto.ColaboradorSecurityDto;
import com.licoreria.proyecto.repository.ColaboradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DetalleColaboradorService implements UserDetailsService {
    private ColaboradorRepository colaboradorRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Colaborador colaborador = colaboradorRepository.findByNomusuario(username);

        return obtenerColaboradorSecurity(colaborador,rolesColaborador(colaborador.getRoles()));
    }

    private List<GrantedAuthority>rolesColaborador(Set<Rol> roles){

        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Rol rol: roles){
            authorityList.add(
                    new SimpleGrantedAuthority(rol.getNomrol())
            );
        }
        return authorityList;
    }

    private UserDetails obtenerColaboradorSecurity(
            Colaborador colaborador, List<GrantedAuthority>authorityList){

        ColaboradorSecurityDto securityDto = new ColaboradorSecurityDto(
                colaborador.getNomusuario(),
                colaborador.getPassword(),
                colaborador.getActivo(),
                true,
                true,
                true,
                authorityList);
        securityDto.setEmail(colaborador.getEmail());
        securityDto.setIdcolaborador(colaborador.getIdcolaborador());

        return securityDto;
    }
}
