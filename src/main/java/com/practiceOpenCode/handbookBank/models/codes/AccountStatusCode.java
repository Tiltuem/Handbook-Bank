package com.practiceOpenCode.handbookBank.models.codes;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_status_codes")
@SQLDelete(sql = "update account_status_codes set deleted=true where id=?")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AccountStatusCode extends AbstractCode{
    @Column(unique = true)
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;

    public AccountStatusCode(String code) {
        super();
        this.code = code;
    }
}
