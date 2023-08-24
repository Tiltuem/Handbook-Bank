package com.practiceOpenCode.handbookBank.models.codes;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "creation_reason_codes")
@SQLDelete(sql = "update creation_reason_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class CreationReasonCode  extends AbstractCode{
    @Column(name = "code", unique = true)
    private String code;

    public CreationReasonCode(String code) {
        super();
        this.code = code;
    }
}
