package com.practiceOpenCode.handbookBank.models.codes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "service_cs_codes")
@SQLDelete(sql = "update service_cs_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceCsCode extends AbstractCode{
    @Column(unique = true)
    @Size(min = 1, max = 1, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "\\d{1}",
            message = "Ошибка: неверный формат")
    private String code;
    public ServiceCsCode(String code) {
        super();
        this.code = code;
    }
}
