package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.models.adapters.AccountRestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "account_restriction_list")
@SQLDelete(sql = "update account_restriction_list set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccRstrList")
@EntityListeners(AuditingEntityListener.class)
public class AccountRestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "AccRstr")
    @XmlJavaTypeAdapter(AccountRestrictionCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_restriction_code_id")
    private AccountRestrictionCode accountRestrictionCode;

    @XmlAttribute(name = "AccRstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "Ошибка: поле не может быть пустым")
    private LocalDate AccountRestrictionDate;

    @XmlAttribute(name = "SuccessorBIC")
    @Pattern(regexp = "(\\d{9})|(^$)",
            message = "Ошибка: неверный формат")
    private String SuccessorBic;

    private Boolean deleted;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    public AccountRestrictionList() {
        this.deleted = false;
    }
}
