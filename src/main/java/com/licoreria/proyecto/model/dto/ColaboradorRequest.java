package com.licoreria.proyecto.model.dto;

import lombok.Data;

@Data
public class ColaboradorRequest {
    private Integer idcolaborador;
    private String nomusuario;
    private String nombres;
    private String apellidos;
    private Boolean activo;
    private String email;
}
