package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "SWBIC")
@SQLDelete(sql = "update SWBIC set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SWBIC")
public class SWBICs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "SWBIC")
    @Size(max = 11, message = "Ошибка: превышена максимальная длина(11)")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String swbic;

    @XmlAttribute(name = "DefaultSWBIC")
    private boolean defaultSWBIC;

    private Boolean deleted;

    public SWBICs() {
        this.deleted = false;
    }
}
