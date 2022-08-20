package com.app.rebuild.repo;

import com.app.rebuild.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation, Long> {
    @Query("select d from Donation d where d.appForm.id=:appFormId")
    List<Donation> getDonationsByAppFormId(long appFormId);
}
