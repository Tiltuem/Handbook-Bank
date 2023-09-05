package com.practiceOpenCode.handbookBank.models.main;


import com.practiceOpenCode.handbookBank.models.adapters.ChangeTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bic_directory_entries")
@SQLDelete(sql = "update bic_directory_entries set deleted=true where id=?")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BICDirectoryEntry")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    public BICDirectoryEntry() {
        this.deleted = false;
    }
}
