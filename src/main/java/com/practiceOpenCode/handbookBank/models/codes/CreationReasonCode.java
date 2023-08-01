package com.practiceOpenCode.handbookBank.models.codes;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "creation_reason_codes")
@SQLDelete(sql = "update creation_reason_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class CreationReasonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public CreationReasonCode(String code) {
        this.code = code;
        deleted = false;
    }
}
