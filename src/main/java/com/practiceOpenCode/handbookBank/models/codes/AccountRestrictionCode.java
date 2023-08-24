package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "account_restriction_codes")
@SQLDelete(sql = "update account_restriction_codes set deleted=true where id=?")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountRestrictionCode extends AbstractCode{
    @Column(unique = true)
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;

    public AccountRestrictionCode(String code) {
        super();
        this.code = code;
    }
}
