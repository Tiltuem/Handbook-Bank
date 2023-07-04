package com.practiceOpenCode.handbookBank.models.directories;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "creation_reasons")
@Data
@NoArgsConstructor
public class CreationReasonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    public CreationReasonCode(String code) {
        this.code = code;
    }
}
