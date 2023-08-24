package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "participant_type_codes")
@SQLDelete(sql = "update participant_type_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class ParticipantTypeCode extends AbstractCode{

    @Column(name = "code", length = 2)
    private String code;
    

    public ParticipantTypeCode(String code) {
        super();
        this.code = code;    
    }
}