package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.models.adapters.AccountStatusCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.RegulationAccountTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import javax.persistence.*;
import javax.validation.constraints.*;
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
import java.util.List;

@Entity
@Table(name = "accounts")
@SQLDelete(sql = "update accounts set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accounts")
@EntityListeners(AuditingEntityListener.class)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "Account")
    @Size(max = 20, message = "Ошибка: превышена максимальная длина(20)")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    private String accountNumber;

    @XmlAttribute(name = "RegulationAccountType")
    @XmlJavaTypeAdapter(RegulationAccountTypeCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_account_type")
    private RegulationAccountTypeCode regulationAccountTypeCode;

    @XmlAttribute(name = "AccountCBRBIC")
    @Size(max = 9, min = 9, message = "Ошибка: введите 9 символов")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    private String bicCbr;

    @XmlAttribute(name = "CK")
    @Size(max = 2, min = 2, message = "Ошибка: введите 2 символа")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    private String controlKey;

    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "Ошибка: поле не может быть пустым")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "AccountStatus")
    @XmlJavaTypeAdapter(AccountStatusCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_status")
    private AccountStatusCode accountStatusCode;

    @XmlElement(name = "AccRstrList", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_restriction_list_id")
    private List<AccountRestrictionList> accountRestrictionList;
    private Boolean deleted;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
    public Accounts() {
        this.deleted = false;
    }
}
