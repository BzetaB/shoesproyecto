package com.licoreria.proyecto.controller;

import com.licoreria.proyecto.model.bd.Rol;
import com.licoreria.proyecto.model.dto.RespuestaGeneral;
import com.licoreria.proyecto.model.dto.RolRequest;
import com.licoreria.proyecto.service.IRolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class RolController {
    private IRolService iRolService;
    @GetMapping("/rol/registro")
    public String frmRol(Model model){
        model.addAttribute("listaroles",
                iRolService.listarRol());
        return "seguridad/frmrol";
    }
    @PostMapping("/rol")
    @ResponseBody
    public RespuestaGeneral registroRol(
            @RequestBody RolRequest rolRequest
            ){
        String mensaje = "Rol registrado correctamente";
        boolean respuesta = true;
        try {
            Rol rol = new Rol();
            rol.setNomrol(rolRequest.getNomrol());
            if (rolRequest.getIdrol()>0){
                rol.setIdrol(rolRequest.getIdrol());
                iRolService.actualizarRol(rol);
            }else {
                iRolService.guardarRol(rol);
            }
        }catch (Exception ex){
            mensaje = "Error al conectarse a la base de datos";
            respuesta = false;
        }
        return RespuestaGeneral.builder().mensaje(mensaje).resultado(respuesta).build();
    }
    @GetMapping("/rol/{idrol}")
    @ResponseBody
    public Rol obtenerRol(
            @PathVariable("idrol") int idrol
    ){
        return iRolService.obtenerRolxId(idrol);
    }
    @GetMapping("/rol")
    @ResponseBody
    public List<Rol> rolList(){
        return iRolService.listarRol();
    }

}
