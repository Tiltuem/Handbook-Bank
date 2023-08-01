package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "restriction_codes")
@SQLDelete(sql = "update restriction_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class RestrictionCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public RestrictionCode(String code) {
        this.code = code;
        deleted = false;
    }}
