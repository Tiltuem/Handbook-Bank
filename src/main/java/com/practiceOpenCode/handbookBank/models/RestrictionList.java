package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.RestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "restriction_list")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RstrList")
public class RestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "Rstr")
    @XmlJavaTypeAdapter(RestrictionCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_code_id")
    private RestrictionCode restrictionCode;

    @XmlAttribute(name = "RstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "restriction_date")
    private LocalDate restrictionDate;
}
