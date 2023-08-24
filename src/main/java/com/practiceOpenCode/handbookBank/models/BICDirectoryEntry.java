package com.practiceOpenCode.handbookBank.models;


import com.practiceOpenCode.handbookBank.models.adapters.ChangeTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;

import java.util.List;

@Entity
@Table(name = "bic_directory_entries")
@SQLDelete(sql = "update bic_directory_entries set deleted=true where id=?")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BICDirectoryEntry")
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "BIC")
    @Size(min = 9, max = 9, message = "Ошибка: неверное количество символов")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    @NotBlank(message = "Ошибка: введите код")
    private String bic;

    @XmlAttribute(name = "ChangeType")
    @XmlJavaTypeAdapter(ChangeTypeCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "change_type_id")
    private ChangeTypeCode changeTypeCode;

    @XmlElement(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_info_id")
    private ParticipantInfo participantInfo;

    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounts_id")
    private List<Accounts> accounts;

    @XmlElement(name = "SWBICS", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SWBICs_id")
    private List<SWBICs> SWBICs;

    private Boolean deleted;

    public BICDirectoryEntry() {
        this.deleted = false;
    }
}
