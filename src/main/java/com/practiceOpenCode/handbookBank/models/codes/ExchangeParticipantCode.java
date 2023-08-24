package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "exchange_participant_codes")
@SQLDelete(sql = "update exchange_participant_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class ExchangeParticipantCode  extends AbstractCode{
    @Column(name = "code", length = 1, unique = true)
    private String code;

    public ExchangeParticipantCode(String code) {
        super();
        this.code = code;
    }}
