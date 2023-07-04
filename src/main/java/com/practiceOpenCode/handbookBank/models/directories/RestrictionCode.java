package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restriction_code")
@Data
@NoArgsConstructor
public class RestrictionCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "description")
    private String description;

    public RestrictionCode(String code) {
        this.code = code;
    }
}
