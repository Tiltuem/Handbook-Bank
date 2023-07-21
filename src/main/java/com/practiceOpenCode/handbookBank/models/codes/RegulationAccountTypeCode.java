package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regulation_account_type_codes")
@Data
@NoArgsConstructor
public class RegulationAccountTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 4)
    private String code;

    public RegulationAccountTypeCode(String code) {
        this.code = code;
    }
}
