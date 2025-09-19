package com.eidiko.controller;

import com.eidiko.dto.GroomingDTO;
import com.eidiko.dto.PetDTO;
import com.eidiko.dto.SupplementsDTO;
import com.eidiko.entity.Pet;
import com.eidiko.service.GroomingService;
import com.eidiko.service.PetService;
import com.eidiko.service.SupplementsService;
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
    private final GroomingService groomingService;
    private final SupplementsService supplementsService;

    @GetMapping("/dashboardView")
    public String dashboardView(HttpSession session, Model model) {
        model.addAttribute("pets", petService.getAllPets());
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("groomingSchedules", groomingService.getAllGroomingSchedules());
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
        return "redirect:/dashboardView";
    }

    @GetMapping("/grooming-form")
    public String grommingForm(HttpSession session, Model model) {
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("grooming", new GroomingDTO());
        model.addAttribute("pets", petService.getAllPets());
        return "grooming";
    }

    @PostMapping("/schedule-grooming")
    public String scheduleGroomForPet(@ModelAttribute("grooming") GroomingDTO groomingDTO, Model model, HttpSession session) {
        groomingDTO.setStatus("Pending");
        groomingService.saveGroomingSchedule(groomingDTO);
        return "redirect:/dashboardView";
    }

    @GetMapping("/supplements-form")
    public String bookSupplementsForPetForm(Model model, HttpSession session) {
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("pets", petService.getAllPets());
        model.addAttribute("supplements", new SupplementsDTO());
        return "supplementsForm";
    }

    @PostMapping("/book-supplements")
    public String bookSupplements(@ModelAttribute("supplements") SupplementsDTO supplementsDTO) {
        supplementsService.bookSupplementsForPet(supplementsDTO);
        return "redirect:/dashboardView";
    }

    @GetMapping("/orders-form")
    public String ordersForm(Model model, HttpSession session) {
        String ownerName = (String) session.getAttribute("ownerName");
        model.addAttribute("ownerName", ownerName);
        model.addAttribute("supplementsList", supplementsService.getAllOrderedSupplements());
        return "orders";
    }
}