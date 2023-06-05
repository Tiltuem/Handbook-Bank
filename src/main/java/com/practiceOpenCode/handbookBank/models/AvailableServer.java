package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "available_servers")
public class AvailableServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private int code;
}