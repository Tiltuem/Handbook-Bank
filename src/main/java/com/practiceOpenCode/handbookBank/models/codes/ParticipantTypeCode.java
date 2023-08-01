package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "participant_type_codes")
@SQLDelete(sql = "update participant_type_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class ParticipantTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public ParticipantTypeCode(String code) {
        this.code = code;
        deleted = false;
    }}