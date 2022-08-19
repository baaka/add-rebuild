package com.example.addrebuild.repo;

import com.example.addrebuild.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepo extends JpaRepository<Donation, Long> {
    List<Donation> getDonationsByAppAppForm(long appFormId);
}
