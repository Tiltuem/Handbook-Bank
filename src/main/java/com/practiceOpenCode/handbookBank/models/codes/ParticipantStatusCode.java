package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant_status_codes")
@Data
@NoArgsConstructor
public class ParticipantStatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public ParticipantStatusCode(String code) {
        this.code = code;
    }
}
