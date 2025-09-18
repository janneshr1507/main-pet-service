package com.eidiko.controller;

import com.eidiko.dto.GroomingDTO;
import com.eidiko.dto.PetDTO;
import com.eidiko.entity.Pet;
import com.eidiko.service.PetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final PetService petService;

    @GetMapping("/dashboardView")
    public String dashboardView(HttpSession session, Model model) {
        model.addAttribute("pets", petService.getAllPets());
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        return "dashboard";
    }

    @GetMapping("/add-pet")
    public String addPetForm(HttpSession session, Model model) {
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("pet", new PetDTO());
        return "addPet";
    }

    @PostMapping("/add-pet")
    public String addPet(@ModelAttribute("pet") PetDTO petDTO, HttpSession session, Model model) {
        petService.savePet(petDTO);
        model.addAttribute("pets", petService.getAllPets());
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        return "dashboard";
    }

    @GetMapping("/grooming-form")
    public String grommingForm(HttpSession session, Model model) {
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("grooming", new GroomingDTO());
        model.addAttribute("pets", petService.getAllPets());
        return "grooming";
    }
}
