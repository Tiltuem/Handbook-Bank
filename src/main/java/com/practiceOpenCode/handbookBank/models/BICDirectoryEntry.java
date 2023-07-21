package com.practiceOpenCode.handbookBank.models;


import com.practiceOpenCode.handbookBank.models.adapters.ChangeTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "bic_directory_entries")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BICDirectoryEntry")
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "BIC")
    @Column(name = "bic")
    private int bic;

    @XmlAttribute(name = "ChangeType")
    @XmlJavaTypeAdapter(ChangeTypeCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "change_type_id")
    private ChangeTypeCode changeTypeCode;

    @XmlElement(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_info_id")
    private ParticipantInfo participantInfo;

    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounts_id")
    private List<Accounts> accounts;

    @XmlElement(name = "SWBICS", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "swbics_id")
    private List<Swbics> swbics;
}
