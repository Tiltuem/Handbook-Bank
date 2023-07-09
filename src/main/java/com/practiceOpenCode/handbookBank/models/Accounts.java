package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.AccountStatusCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.RegulationAccountTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.directories.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.directories.RegulationAccountTypeCode;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accounts")
@Component
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "Account")
    @Column(name = "account_number", length = 20)
    private String accountNumber;

    @XmlAttribute(name = "RegulationAccountType")
    @XmlJavaTypeAdapter(RegulationAccountTypeCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_account_type")
    private RegulationAccountTypeCode regulationAccountTypeCode;

    @XmlAttribute(name = "AccountCBRBIC")
    @Column(name = "bic_cbr")
    private long bicCbr;

    @XmlAttribute(name = "CK")
    @Column(name = "control_key", length = 2)
    private String controlKey;

    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "date_in")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "date_out")
    private LocalDate dateOut;

    @XmlAttribute(name = "AccountStatus")
    @XmlJavaTypeAdapter(AccountStatusCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_status")
    private AccountStatusCode accountStatusCode;

    @XmlElement(name = "AccRstrList", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_restriction_list_id")
    private List<AccountRestrictionList> accountRestrictionList;
}
