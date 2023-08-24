package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "change_type_codes")
@SQLDelete(sql = "update change_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class ChangeTypeCode extends AbstractCode{
    @Column(name = "code", unique = true)
    private String code;
    public ChangeTypeCode(String code) {
        super();
        this.code = code;
    }
}
