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
@Table(name = "participant_type_codes")
@SQLDelete(sql = "update participant_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParticipantTypeCode extends AbstractCode{

    @Column(unique = true)
    @Size(max = 2, message = "Ошибка: неверное количество символов")
    @NotBlank(message = "Ошибка: введите код")
    @Pattern(regexp = "\\d{2}",
            message = "Ошибка: неверный формат")
    private String code;
    

    public ParticipantTypeCode(String code) {
        super();
        this.code = code;    
    }
}