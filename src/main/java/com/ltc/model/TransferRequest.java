package com.ltc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_requests")
public class TransferRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String patientHealthCard;
    private String patientCareNeeds;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private LTCFacility facility;

    @OneToOne
    @JoinColumn(name = "bed_id")
    private Bed assignedBed;

    @ManyToOne
    @JoinColumn(name = "requested_by")
    private User requestedBy;

    private String status;  // pending, accepted, declined, completed

    
    private LocalDateTime createdAt;

    
    private LocalDateTime updatedAt;

    private String notes;

    // Constructors
    public TransferRequest() {}

    public TransferRequest(String patientName, LTCFacility facility, User requestedBy) {
        this.patientName = patientName;
        this.facility = facility;
        this.requestedBy = requestedBy;
        this.status = "pending";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientHealthCard() { return patientHealthCard; }
    public void setPatientHealthCard(String patientHealthCard) { this.patientHealthCard = patientHealthCard; }

    public String getPatientCareNeeds() { return patientCareNeeds; }
    public void setPatientCareNeeds(String patientCareNeeds) { this.patientCareNeeds = patientCareNeeds; }

    public LTCFacility getFacility() { return facility; }
    public void setFacility(LTCFacility facility) { this.facility = facility; }

    public Bed getAssignedBed() { return assignedBed; }
    public void setAssignedBed(Bed assignedBed) { this.assignedBed = assignedBed; }

    public User getRequestedBy() { return requestedBy; }
    public void setRequestedBy(User requestedBy) { this.requestedBy = requestedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}