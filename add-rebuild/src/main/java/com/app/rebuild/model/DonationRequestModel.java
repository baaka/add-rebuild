package com.app.rebuild.model;

import com.app.rebuild.domain.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DonationRequestModel {
    private long appFormId;
    private double amount;
    private Currency currency;
}
