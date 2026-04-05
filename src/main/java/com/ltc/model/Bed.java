package com.ltc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "beds")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bedNumber;
    private String bedType;  // Normal, Private, Semi-Private, Specialized, Bariatric, Respite
    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private LTCFacility facility;

    @OneToOne(mappedBy = "assignedBed")
    private TransferRequest currentTransfer;

    // Constructors
    public Bed() {}

    public Bed(String bedNumber, String bedType, LTCFacility facility) {
        this.bedNumber = bedNumber;
        this.bedType = bedType;
        this.facility = facility;
        this.isOccupied = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    public String getBedType() { return bedType; }
    public void setBedType(String bedType) { this.bedType = bedType; }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    public LTCFacility getFacility() { return facility; }
    public void setFacility(LTCFacility facility) { this.facility = facility; }

    public TransferRequest getCurrentTransfer() { return currentTransfer; }
    public void setCurrentTransfer(TransferRequest currentTransfer) { this.currentTransfer = currentTransfer; }
}