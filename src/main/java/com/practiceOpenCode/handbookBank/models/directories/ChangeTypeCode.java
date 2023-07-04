package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "change_type")
@Data
@NoArgsConstructor
public class ChangeTypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    public ChangeTypeCode(String code) {
        this.code = code;
    }
}
