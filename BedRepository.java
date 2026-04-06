package com.ltc.repository;

import com.ltc.model.Bed;
import com.ltc.model.LTCFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByFacility(LTCFacility facility);
    List<Bed> findByBedTypeAndIsOccupiedFalse(String bedType);
    List<Bed> findByFacilityAndIsOccupiedFalse(LTCFacility facility);
    long countByFacilityAndIsOccupiedFalse(LTCFacility facility);
}