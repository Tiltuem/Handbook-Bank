package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Entity
@Table(name = "swbic")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SWBIC")
public class Swbics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "SWBIC")
    @Column(name = "swbic", length = 11)
    private String swbic;

    @XmlAttribute(name = "DefaultSWBIC")
    @Column(name = "default_swbic")
    private boolean DefaultSwbic;
}
