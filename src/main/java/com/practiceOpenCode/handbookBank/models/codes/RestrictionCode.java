package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "restriction_codes")
@SQLDelete(sql = "update restriction_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class RestrictionCode extends AbstractCode {
    @Column(length = 4)
    private String code;

    public RestrictionCode(String code) {
        super();
        this.code = code;
    }
}
