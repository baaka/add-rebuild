package com.example.addrebuild.repo;

import com.example.addrebuild.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation, Long> {
    List<Donation> getDonationsByAppForm(long appFormId);
}
