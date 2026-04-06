package com.ltc.controller;

import com.ltc.model.*;
import com.ltc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HospitalController {

    @Autowired
    private FacilityRepository facilityRepository;
    
    @Autowired
    private BedRepository bedRepository;
    
    @Autowired
    private TransferRepository transferRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hospital/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("userName", user != null ? user.getName() : "Staff");
        
        // Get all facilities
        List<LTCFacility> facilities = facilityRepository.findAll();
        
        // Calculate available beds for each facility
        for (LTCFacility facility : facilities) {
            long available = bedRepository.countByFacilityAndIsOccupiedFalse(facility);
            facility.setAvailableBeds((int) available);
        }
        
        model.addAttribute("facilities", facilities);
        
        // Get user's transfer requests
        List<TransferRequest> userRequests = transferRepository.findByRequestedBy(user);
        model.addAttribute("requests", userRequests);
        
        return "hospital-dashboard";
    }

    @GetMapping("/hospital/filter")
public String filterFacilities(@RequestParam(required = false) String facilityName,
                                @RequestParam(required = false) String bedType,
                                @RequestParam(required = false) String availability,
                                Model model,
                                HttpSession session) {
    
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) return "redirect:/login";
    
    List<LTCFacility> facilities = facilityRepository.findAll();

    //  Filter by facility name
    if (facilityName != null && !facilityName.isEmpty()) {
        facilities = facilities.stream()
                .filter(f -> f.getName().toLowerCase().contains(facilityName.toLowerCase()))
                .collect(Collectors.toList());
    }

    //  Filter by bed type
    if (bedType != null && !bedType.isEmpty()) {

        // Get beds matching type (only available ones)
        List<Bed> beds = bedRepository.findByBedTypeAndIsOccupiedFalse(bedType);

        // Extract facilities that have those beds
        List<LTCFacility> facilitiesWithBedType = beds.stream()
                .map(Bed::getFacility)
                .distinct()
                .collect(Collectors.toList());

        facilities = facilities.stream()
                .filter(facilitiesWithBedType::contains)
                .collect(Collectors.toList());
    }

    //  Calculate available beds
    for (LTCFacility facility : facilities) {
        long available = bedRepository.countByFacilityAndIsOccupiedFalse(facility);
        facility.setAvailableBeds((int) available);
    }

    //  Filter by availability
    if ("Available".equals(availability)) {
        facilities = facilities.stream()
                .filter(f -> f.getAvailableBeds() > 0)
                .collect(Collectors.toList());
    }

    model.addAttribute("facilities", facilities);
    model.addAttribute("requests", 
        transferRepository.findByRequestedBy(
            userRepository.findById(userId).orElse(null)
        )
    );

    return "hospital-dashboard";
}

    @GetMapping("/hospital/transfer-form")
    public String showTransferForm(@RequestParam(required = false) Long facilityId,
                                    Model model,
                                    HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        if (facilityId != null) {
            LTCFacility facility = facilityRepository.findById(facilityId).orElse(null);
            model.addAttribute("selectedFacility", facility);
        }
        
        model.addAttribute("facilities", facilityRepository.findAll());
        return "transfer";
    }

    @PostMapping("/hospital/submit-transfer")
    public String submitTransfer(@RequestParam Long facilityId,
                                  @RequestParam String patientName,
                                  @RequestParam String patientCareNeeds,
                                  HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        LTCFacility facility = facilityRepository.findById(facilityId).orElse(null);
        User requester = userRepository.findById(userId).orElse(null);
        
        if (facility != null && requester != null) {
            TransferRequest request = new TransferRequest();
            request.setPatientName(patientName);
            request.setPatientCareNeeds(patientCareNeeds);
            request.setFacility(facility);
            request.setRequestedBy(requester);
            request.setStatus("pending");
            request.setCreatedAt(LocalDateTime.now());
            request.setUpdatedAt( LocalDateTime.now());
            
            transferRepository.save(request);
        }
        
        return "redirect:/hospital/dashboard";
    }
}