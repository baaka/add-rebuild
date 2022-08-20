package com.app.rebuild.api;

import com.app.rebuild.AppConstants;
import com.app.rebuild.domain.Donation;
import com.app.rebuild.model.DonationRequestModel;
import com.app.rebuild.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.APP_REST_CONTEXT_PATH + "/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping
    public List<Donation> getDonations() {
        return donationService.getDonations();
    }

    @GetMapping("/{appFormId}")
    public List<Donation> getDonations(@PathVariable long appFormId) {
        return donationService.getDonationsByAppFormId(appFormId);
    }

    @PostMapping
    public Donation addAppForm(@RequestBody DonationRequestModel requestModel) {
        return donationService.addDonation(requestModel);
    }

}
