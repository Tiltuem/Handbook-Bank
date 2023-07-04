package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regulation_account_types")
@Data
@NoArgsConstructor
public class RegulationAccountTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "description")
    private String description;

    public RegulationAccountTypeCode(String code) {
        this.code = code;
    }
}
