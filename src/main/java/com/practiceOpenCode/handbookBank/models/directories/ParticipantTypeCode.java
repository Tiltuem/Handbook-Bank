package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant_types")
@Data
@NoArgsConstructor
public class ParticipantTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "description")
    private String description;

    public ParticipantTypeCode(String code) {
        this.code = code;
    }
}