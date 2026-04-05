package com.ltc.repository;

import com.ltc.model.LTCFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<LTCFacility, Long> {
    List<LTCFacility> findByLocationContainingIgnoreCase(String location);
}