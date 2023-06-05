package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "participant_status")
public class ParticipantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;
}
