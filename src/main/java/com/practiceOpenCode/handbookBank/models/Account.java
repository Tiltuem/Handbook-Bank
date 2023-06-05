package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type")
    private AccountType accountType;

    @Column(name = "bic_cbr")
    private String bicCbr;

    @Column(name = "control_key")
    private String controlKey;

    @Column(name = "inclusion_date")
    private LocalDate inclusionDate;

    @Column(name = "exclusion_date")
    private LocalDate exclusionDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_status")
    private AccountStatus accountStatus;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Participant participant;
}
