package com.practiceOpenCode.handbookBank.models.codes;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "creation_reason_codes")
@Data
@NoArgsConstructor
public class CreationReasonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public CreationReasonCode(String code) {
        this.code = code;
    }
}
