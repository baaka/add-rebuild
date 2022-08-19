package com.example.addrebuild.service;

import com.example.addrebuild.domain.AppForm;
import com.example.addrebuild.domain.Donation;
import com.example.addrebuild.domain.User;
import com.example.addrebuild.exception.AppException;
import com.example.addrebuild.model.DonationRequestModel;
import com.example.addrebuild.repo.DonationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Donation addDonation(DonationRequestModel request) {
        Donation donation = new Donation();
        donation.setAmount(request.getAmount());
        donation.setCurrency(request.getCurrency());

        User author = userService.getById(request.getAuthorId());
        donation.setAuthor(author);

        AppForm appForm = appFormService.getAppFormById(request.getAppFormId());
        donation.setAppForm(appForm);

        return donationRepo.saveAndFlush(donation);
    }

}
