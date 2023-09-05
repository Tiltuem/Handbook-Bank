package com.practiceOpenCode.handbookBank.models.main;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "SWBIC")
@SQLDelete(sql = "update SWBIC set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SWBIC")
@EntityListeners(AuditingEntityListener.class)

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

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    private Boolean deleted;

    public SWBICs() {
        this.deleted = false;
    }
}
