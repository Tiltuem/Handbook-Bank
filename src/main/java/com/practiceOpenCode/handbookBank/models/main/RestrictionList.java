package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.adapters.RestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "restriction_list")
@SQLDelete(sql = "update restriction_list set deleted=true where id=?")
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RstrList")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "Rstr")
    @XmlJavaTypeAdapter(RestrictionCodeAdapter.class)
    private RestrictionCode restrictionCode;

    @XmlAttribute(name = "RstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
