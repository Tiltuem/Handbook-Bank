package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "service_cs_codes")
@SQLDelete(sql = "update service_cs_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class ServiceCsCode extends AbstractCode{
    @Column(length = 1)
    private String code;
    public ServiceCsCode(String code) {
        super();
        this.code = code;
    }
}
