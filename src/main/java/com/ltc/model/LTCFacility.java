package com.ltc.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ltc_facilities")
public class LTCFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String phone;
    private int totalBeds;
    private int availableBeds;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<Bed> beds;

    // Constructors
    public LTCFacility() {}

    public LTCFacility(String name, String location, String phone, int totalBeds) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.totalBeds = totalBeds;
        this.availableBeds = totalBeds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getTotalBeds() { return totalBeds; }
    public void setTotalBeds(int totalBeds) { this.totalBeds = totalBeds; }

    public int getAvailableBeds() { return availableBeds; }
    public void setAvailableBeds(int availableBeds) { this.availableBeds = availableBeds; }

    public List<Bed> getBeds() { return beds; }
    public void setBeds(List<Bed> beds) { this.beds = beds; }
}