package com.eidiko.controller;

import com.eidiko.dto.OwnerDTO;
import com.eidiko.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final OwnerService ownerService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register-form")
    private String registerForm(Model model) {
        model.addAttribute("register", new OwnerDTO());
        return "register";
    }

    @PostMapping("/register-form")
    private String registerOwner(@ModelAttribute("register") OwnerDTO ownerDTO) {
        ownerService.saveOwner(ownerDTO);
        return "redirect:/login";
    }
}
