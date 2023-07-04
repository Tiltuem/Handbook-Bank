package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant_status")
@Data
@NoArgsConstructor
public class ParticipantStatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    public ParticipantStatusCode(String code) {
        this.code = code;
    }
}
