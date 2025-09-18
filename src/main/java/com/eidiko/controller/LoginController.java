package com.eidiko.controller;

import com.eidiko.dto.LoginDTO;
import com.eidiko.entity.Pet;
import com.eidiko.service.PetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PetService petService;

    @GetMapping("/login-form")
    public String loginPage(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "login";
    }

    @PostMapping("/validate-login")
    public String validateCredentials(@ModelAttribute("login") LoginDTO loginDTO, HttpSession session, Model model) {
        if(loginDTO.getEmail().equals("jannesh@gmail.com") && loginDTO.getPassword().equals("sarasu10")) {
            model.addAttribute("ownerName", "Jannesh Rao");
            session.setAttribute("ownerName", "Jannesh Rao");
            List<Pet> pets = petService.getAllPets();
            model.addAttribute("pets", pets);
            return "/dashboard";
        } else {
            return "redirect:/login-form";
        }
    }
}
