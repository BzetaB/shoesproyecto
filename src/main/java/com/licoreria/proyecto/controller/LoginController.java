package com.licoreria.proyecto.controller;

import com.licoreria.proyecto.model.dto.ColaboradorSecurityDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        ColaboradorSecurityDto securityDto =
                (ColaboradorSecurityDto) userDetails;
        session.setAttribute("nomusuario",
                securityDto.getUsername());
        return "auth/home";
    }
}
