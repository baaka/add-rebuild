package com.example.addrebuild.api;

import com.example.addrebuild.AppConstants;
import com.example.addrebuild.domain.Donation;
import com.example.addrebuild.model.DonationRequestModel;
import com.example.addrebuild.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.APP_REST_CONTEXT_PATH + "/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public Donation addAppForm(DonationRequestModel requestModel) {
        return donationService.addDonation(requestModel);
    }

}
