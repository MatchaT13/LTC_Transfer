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


@Controller
public class LTCController {

    @Autowired
    private FacilityRepository facilityRepository;
    
    @Autowired
    private BedRepository bedRepository;
    
    @Autowired
    private TransferRepository transferRepository;
    


    @GetMapping("/ltc/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        // For demo, use first facility. In real app, link user to facility
        LTCFacility facility = facilityRepository.findAll().stream().findFirst().orElse(null);
        
        if (facility != null) {
            List<Bed> beds = bedRepository.findByFacility(facility);
            long vacantBeds = bedRepository.countByFacilityAndIsOccupiedFalse(facility);
            List<TransferRequest> pendingRequests = transferRepository.findByFacilityAndStatus(facility, "pending");
            
            model.addAttribute("facility", facility);
            model.addAttribute("beds", beds);
            model.addAttribute("vacantBeds", vacantBeds);
            model.addAttribute("totalBeds", facility.getTotalBeds());
            model.addAttribute("pendingRequests", pendingRequests);
        }
        
        return "ltc-dashboard";
    }

    @GetMapping("/ltc/requests")
    public String showRequests(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        LTCFacility facility = facilityRepository.findAll().stream().findFirst().orElse(null);
        
        if (facility != null) {
            List<TransferRequest> pendingRequests = transferRepository.findByFacilityAndStatus(facility, "pending");
            model.addAttribute("pendingRequests", pendingRequests);
        }
        
        return "accept-decline";
    }

    @PostMapping("/ltc/respond")
    public String respondToRequest(@RequestParam Long requestId,
                                    @RequestParam String decision,
                                    HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        TransferRequest request = transferRepository.findById(requestId).orElse(null);
        
        if (request != null) {
            if ("accept".equals(decision)) {
                // Find available bed
                List<Bed> availableBeds = bedRepository.findByFacilityAndIsOccupiedFalse(request.getFacility());
                if (!availableBeds.isEmpty()) {
                    Bed bed = availableBeds.get(0);
                    bed.setOccupied(true);
                    bedRepository.save(bed);
                    
                    request.setAssignedBed(bed);
                    request.setStatus("accepted");
                    
                    // Update facility available beds
                    LTCFacility facility = request.getFacility();
                    long available = bedRepository.countByFacilityAndIsOccupiedFalse(facility);
                    facility.setAvailableBeds((int) available);
                    facilityRepository.save(facility);
                }
            } else if ("decline".equals(decision)) {
                request.setStatus("declined");
            }
            
            request.setUpdatedAt(LocalDateTime.now());
            transferRepository.save(request);
        }
        
        return "redirect:/ltc/dashboard";
    }

    @PostMapping("/ltc/discharge")
    public String dischargePatient(@RequestParam Long bedId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        
        Bed bed = bedRepository.findById(bedId).orElse(null);
        
        if (bed != null && bed.isOccupied()) {
            bed.setOccupied(false);
            bedRepository.save(bed);
            
            LTCFacility facility = bed.getFacility();
            long available = bedRepository.countByFacilityAndIsOccupiedFalse(facility);
            facility.setAvailableBeds((int) available);
            facilityRepository.save(facility);
        }
        
        return "redirect:/ltc/dashboard";
    }
}