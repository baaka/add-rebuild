package com.app.rebuild.service;

import com.app.rebuild.domain.AppForm;
import com.app.rebuild.domain.Donation;
import com.app.rebuild.exception.AppException;
import com.app.rebuild.model.DonationRequestModel;
import com.app.rebuild.repo.DonationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = AppException.class)
public class DonationService {

    private final DonationRepo donationRepo;

    private final AppFormService appFormService;

    private UserService userService;

    public DonationService(DonationRepo donationRepo, AppFormService appFormService, UserService userService) {
        this.donationRepo = donationRepo;
        this.appFormService = appFormService;
        this.userService = userService;
    }

    public List<Donation> getDonations() {
        return donationRepo.findAllByOrderByCreationTimeDesc();
    }

    public List<Donation> getDonationsByAppFormId(long appFormId) {
        return donationRepo.getDonationsByAppFormId(appFormId);
    }

    public Donation addDonation(DonationRequestModel request) {
        Donation donation = new Donation();
        donation.setAmount(request.getAmount());
        donation.setCurrency(request.getCurrency());
        donation.setAuthor(userService.getCurrentUser());
        donation.setCreationTime(new Date());

        AppForm appForm = appFormService.getAppFormById(request.getAppFormId());
        donation.setAppForm(appForm);

        return donationRepo.saveAndFlush(donation);
    }

}
