package com.example.addrebuild.model;

import com.example.addrebuild.domain.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DonationRequestModel {
    private long authorId;
    private long appFormId;
    private double amount;
    private Currency currency;
}
