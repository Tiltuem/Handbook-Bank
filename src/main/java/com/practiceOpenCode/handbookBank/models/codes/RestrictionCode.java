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
@Table(name = "restriction_codes")
@SQLDelete(sql = "update restriction_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestrictionCode extends AbstractCode {
    @Column(unique = true)
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;

    public RestrictionCode(String code) {
        super();
        this.code = code;
    }
}
