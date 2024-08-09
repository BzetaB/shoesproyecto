package com.licoreria.proyecto.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcolaborador;
    private String nomusuario;
    private String password;
    private String nombres;
    private String apellidos;
    private String email;
    private Boolean activo;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "colaborador_rol",
            joinColumns = @JoinColumn(name = "idcolaborador"),
            inverseJoinColumns = @JoinColumn(name = "idrol"))
    private Set<Rol> roles;


}
