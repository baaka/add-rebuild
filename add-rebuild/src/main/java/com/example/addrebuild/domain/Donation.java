package com.example.addrebuild.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DONATION")
@Getter
@Setter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_USER_ID")
    private User author;

    @ManyToOne
    @JoinColumn(name = "APP_FORM_ID")
    private AppForm appForm;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    @Column(name = "AMOUNT")
    private double amount;

    @Enumerated
    @Column(name = "CURRENCY")
    private Currency currency;
}
