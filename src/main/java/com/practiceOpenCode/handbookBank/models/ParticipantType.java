package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "participant_types")
public class ParticipantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private int code;
}