package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "participant_status_codes")
@SQLDelete(sql = "update participant_status_codes set deleted=true where id=?")
@Data
@NoArgsConstructor
public class ParticipantStatusCode  extends AbstractCode{
    @Column(name = "code")
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public ParticipantStatusCode(String code) {
        super();
        this.code = code;
    }
}
