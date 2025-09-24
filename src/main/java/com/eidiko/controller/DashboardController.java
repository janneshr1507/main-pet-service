package com.eidiko.controller;

import com.eidiko.util.OwnerDetails;
import com.eidiko.dto.GroomingDTO;
import com.eidiko.dto.PetDTO;
import com.eidiko.dto.SupplementsDTO;
import com.eidiko.service.GroomingService;
import com.eidiko.service.PetService;
import com.eidiko.service.SupplementsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    //private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    @GetMapping("/dashboardView")
    public String dashboardView(@AuthenticationPrincipal OwnerDetails ownerDetails, Model model) {
        model.addAttribute("pets", petService.getAllPets());
        model.addAttribute("ownerName", ownerDetails.getName());
        model.addAttribute("groomingSchedules", groomingService.getAllGroomingSchedules());
        return "dashboard";
    }

    @GetMapping("/add-pet")
    public String addPetForm(@AuthenticationPrincipal OwnerDetails ownerDetails, Model model) {
        model.addAttribute("ownerName", ownerDetails.getName());
        model.addAttribute("pet", new PetDTO());
        return "addPet";
    }

    @PostMapping("/add-pet")
    public String addPet(@AuthenticationPrincipal OwnerDetails ownerDetails, @ModelAttribute("pet") PetDTO petDTO, Model model) {
        //log.debug("Add Pet Request Initiated");
        petDTO.setOwnerId(ownerDetails.getId());
        petService.savePet(petDTO);
        return "redirect:/dashboardView";
    }

    @GetMapping("/grooming-form")
    public String grommingForm(@AuthenticationPrincipal OwnerDetails ownerDetails, Model model) {
        model.addAttribute("ownerName", ownerDetails.getName());
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
    public String bookSupplementsForPetForm(@AuthenticationPrincipal OwnerDetails ownerDetails, Model model) {
        model.addAttribute("ownerName", ownerDetails.getName());
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
    public String ordersForm(@AuthenticationPrincipal OwnerDetails ownerDetails, Model model) {
        model.addAttribute("ownerName", ownerDetails.getName());
        model.addAttribute("supplementsList", supplementsService.getAllOrderedSupplements());
        return "orders";
    }
}