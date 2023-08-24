package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "info_type_codes")
@SQLDelete(sql = "update info_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class InformationTypeCode  extends AbstractCode{
    @Column(name = "code")
    private String code;

    public InformationTypeCode(String code) {
        super();
        this.code = code;
    }
}
