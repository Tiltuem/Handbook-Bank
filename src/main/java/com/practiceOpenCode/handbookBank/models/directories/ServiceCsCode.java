package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_cs_codes")
@Data
@NoArgsConstructor
public class ServiceCsCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 1)
    private String code;

    public ServiceCsCode(String code) {
        this.code = code;
    }
}
