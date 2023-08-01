package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "service_cs_codes")
@SQLDelete(sql = "update service_cs_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
public class ServiceCsCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 1)
    private String code;

    @Column(name = "deleted")
    private Boolean deleted;

    public ServiceCsCode(String code) {
        this.code = code;
        deleted = false;
    }}
