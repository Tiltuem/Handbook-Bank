package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exchange_participant")
public class ExchangeParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private int code;
}
