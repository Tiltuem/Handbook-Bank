package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.AccountRestrictionCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "account_restriction_list")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccRstrList")
public class AccountRestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "AccRstr")
    @XmlJavaTypeAdapter(AccountRestrictionCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_restriction_code_id")
    private AccountRestrictionCode accountRestrictionCode;

    @XmlAttribute(name = "AccRstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "account_restriction_date")
    private LocalDate AccountRestrictionDate;

    @XmlAttribute(name = "SuccessorBIC")
    @Column(name = "successor_bic")
    private long SuccessorBic;
}
