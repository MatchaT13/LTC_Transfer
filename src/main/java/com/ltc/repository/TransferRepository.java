package com.ltc.repository;

import com.ltc.model.TransferRequest;
import com.ltc.model.User;
import com.ltc.model.LTCFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferRequest, Long> {
    List<TransferRequest> findByRequestedBy(User requestedBy);
    List<TransferRequest> findByFacility(LTCFacility facility);
    List<TransferRequest> findByFacilityAndStatus(LTCFacility facility, String status);
}
