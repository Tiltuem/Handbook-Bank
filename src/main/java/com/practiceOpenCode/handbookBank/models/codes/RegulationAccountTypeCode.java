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
@Table(name = "regulation_account_type_codes")
@SQLDelete(sql = "update regulation_account_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegulationAccountTypeCode extends AbstractCode{
    @Column(unique = true)
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;


    public RegulationAccountTypeCode(String code) {
        super();
        this.code = code;
    }
}
