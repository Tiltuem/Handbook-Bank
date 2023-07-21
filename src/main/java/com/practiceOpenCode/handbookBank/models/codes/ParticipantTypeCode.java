package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant_type_codes")
@Data
@NoArgsConstructor
public class ParticipantTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 2)
    private String code;

    public ParticipantTypeCode(String code) {
        this.code = code;
    }
}