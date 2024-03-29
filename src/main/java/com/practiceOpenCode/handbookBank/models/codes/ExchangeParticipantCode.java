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
@Table(name = "exchange_participant_codes")
@SQLDelete(sql = "update exchange_participant_codes set deleted=true where id=?")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ExchangeParticipantCode  extends AbstractCode {
    @Column(unique = true)
    @Size(min = 1, max = 1, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "\\d",
            message = "Ошибка: неверный формат")
    private String code;

    public ExchangeParticipantCode(String code) {
        this.code = code;
    }
}
