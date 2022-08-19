package com.example.addrebuild.repo;

import com.example.addrebuild.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepo extends JpaRepository<Donation, Long> {
}
