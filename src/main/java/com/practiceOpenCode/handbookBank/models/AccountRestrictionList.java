package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.AccountRestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Entity
@Table(name = "account_restriction_list")
@SQLDelete(sql = "update account_restriction_list set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccRstrList")

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

    public AccountRestrictionList() {
        this.deleted = false;
    }
}
