package com.practiceOpenCode.handbookBank.models.codes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Column(unique = true)
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;
    public ChangeTypeCode(String code) {
        super();
        this.code = code;
    }
}
