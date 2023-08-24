package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "regulation_account_type_codes")
@SQLDelete(sql = "update regulation_account_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class RegulationAccountTypeCode extends AbstractCode{
    @Column(length = 4)
    private String code;


    public RegulationAccountTypeCode(String code) {
        super();
        this.code = code;
    }
}
