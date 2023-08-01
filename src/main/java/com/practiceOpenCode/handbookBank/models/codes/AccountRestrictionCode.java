package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;



@Entity
@Table(name = "account_restriction_codes")
@SQLDelete(sql = "update account_restriction_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class AccountRestrictionCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    @Size(min = 4, max = 4, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "[A-Z]{4}",
            message = "Ошибка: неверный формат")
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public AccountRestrictionCode(String code) {
        this.code = code;
        deleted = false;
    }
}
