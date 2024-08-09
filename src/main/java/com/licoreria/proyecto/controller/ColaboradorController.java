package com.licoreria.proyecto.controller;

import com.licoreria.proyecto.model.bd.Colaborador;
import com.licoreria.proyecto.model.dto.ColaboradorRequest;
import com.licoreria.proyecto.model.dto.RespuestaGeneral;
import com.licoreria.proyecto.service.IColaboradorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class ColaboradorController {
    private IColaboradorService iColaboradorService;
    @GetMapping("/colaborador/registro")
    public String frmColaborador(Model model){
        model.addAttribute("listacolaborador",
                iColaboradorService.listarColaborador());
        return "seguridad/frmcolaborador";
    }
    @PostMapping("/colaborador")
    @ResponseBody
    public RespuestaGeneral registrarColaborador(
            @RequestBody ColaboradorRequest colaboradorRequest){
        String mensaje = "Usuario registrado correctamente";
        boolean respuesta = true;
        try {
            Colaborador colaborador = new Colaborador();

            colaborador.setNombres(colaboradorRequest.getNombres());
            colaborador.setApellidos(colaboradorRequest.getApellidos());
            if (colaboradorRequest.getIdcolaborador()>0){
                colaborador.setIdcolaborador(colaboradorRequest.getIdcolaborador());
                colaborador.setActivo(colaboradorRequest.getActivo());
                iColaboradorService.actualizarColaborador(colaborador);
            }else {
                colaborador.setNomusuario(colaboradorRequest.getNomusuario());
                colaborador.setEmail(colaboradorRequest.getEmail());
                iColaboradorService.guardarColaborador(colaborador);
            }
        }catch (Exception ex){
            mensaje = "Error al conectarse a la base de datos";
            respuesta = false;
        }
        return RespuestaGeneral.builder().mensaje(mensaje).resultado(respuesta).build();
    }

    @GetMapping("/colaborador/{id}")
    @ResponseBody
    public Colaborador obtenerColaborador(
            @PathVariable("id") int id
    ){
        return iColaboradorService.obtenerColaboradorxId(id);
    }
    @GetMapping("/colaborador")
    @ResponseBody
    public List<Colaborador> colaboradorList(){
        return iColaboradorService.listarColaborador();
    }





}
