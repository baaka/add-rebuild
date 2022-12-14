package com.app.rebuild.domain;

import com.app.rebuild.domain.auth.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APP_FORM")
@Getter
@Setter
@NoArgsConstructor
public class AppForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_USER_ID")
    private User author;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    @Column(name = "AMOUNT_REQUESTED")
    private Double amountRequested;

    @Enumerated
    @Column(name = "AMOUNT_REQUESTED_CURRENCY")
    private Currency amountRequestedCurrency;

    @Enumerated
    @Column(name = "TYPE")
    private AppFormType type;

}
