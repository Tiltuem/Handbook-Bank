package com.practiceOpenCode.handbookBank.models.main;


import com.practiceOpenCode.handbookBank.adapters.ChangeTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bic_directory_entries")
@SQLDelete(sql = "update bic_directory_entries set deleted=true where id=?")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BICDirectoryEntry")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "BIC")
    @Size(min = 9, max = 9, message = "Ошибка: введите 9 символов")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    @NotBlank(message = "Ошибка: введите код")
    private String bic;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "ChangeType")
    @XmlJavaTypeAdapter(ChangeTypeCodeAdapter.class)
    private ChangeTypeCode changeTypeCode;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @XmlElement(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
    private ParticipantInfo participantInfo;

    @OneToMany(cascade = CascadeType.PERSIST)
    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    private List<Accounts> accounts;

    @OneToMany(cascade = CascadeType.PERSIST)
    @XmlElement(name = "SWBICS", namespace = "urn:cbr-ru:ed:v2.0")
    private List<Swbics> swbics;

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
