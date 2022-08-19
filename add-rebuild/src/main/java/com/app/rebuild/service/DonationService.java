package com.app.rebuild.service;

import com.app.rebuild.domain.AppForm;
import com.app.rebuild.domain.Donation;
import com.app.rebuild.domain.User;
import com.app.rebuild.exception.AppException;
import com.app.rebuild.model.DonationRequestModel;
import com.app.rebuild.repo.DonationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Donation> getDonationsByAppFormId(long appFormId) {
        return donationRepo.getDonationsByAppForm(appFormId);
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
