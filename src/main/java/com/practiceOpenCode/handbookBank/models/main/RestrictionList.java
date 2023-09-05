package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.RestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "restriction_list")
@SQLDelete(sql = "update restriction_list set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RstrList")
@EntityListeners(AuditingEntityListener.class)
public class RestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "Rstr")
    @XmlJavaTypeAdapter(RestrictionCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_code_id")
    private RestrictionCode restrictionCode;

    @XmlAttribute(name = "RstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "Ошибка: поле не может быть пустым")
    private LocalDate restrictionDate;

    private Boolean deleted;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    public RestrictionList() {
        this.deleted = false;
    }
}
