package com.practiceOpenCode.handbookBank.models;


import jakarta.persistence.*;

@Entity
@Table(name = "creation_reasons")
public class CreationReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;
}
