package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "regulation_account_type_codes")
@SQLDelete(sql = "update regulation_account_type_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class RegulationAccountTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public RegulationAccountTypeCode(String code) {
        this.code = code;
        deleted = false;
    }}
