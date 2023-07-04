package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exchange_participant")
@Data
@NoArgsConstructor
public class ExchangeParticipantCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 1)
    private String code;

    @Column(name = "description")
    private String description;

    public ExchangeParticipantCode(String code) {
        this.code = code;
    }
}
