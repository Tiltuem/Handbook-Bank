package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "change_type_codes")
@Data
@NoArgsConstructor
public class ChangeTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public ChangeTypeCode(String code) {
        this.code = code;
    }
}
