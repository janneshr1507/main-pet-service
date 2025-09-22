package com.eidiko.controller;

import com.eidiko.dto.GroomingDTO;
import com.eidiko.dto.LoginDTO;
import com.eidiko.entity.Pet;
import com.eidiko.service.GroomingService;
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
    private final GroomingService groomingService;

    @GetMapping("/login-form")
    public String loginPage(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "loginPage";
    }
}
