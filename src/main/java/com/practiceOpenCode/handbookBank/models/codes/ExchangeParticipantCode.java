package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exchange_participant_codes")
@Data
@NoArgsConstructor
public class ExchangeParticipantCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 1)
    private String code;

    public ExchangeParticipantCode(String code) {
        this.code = code;
    }
}
