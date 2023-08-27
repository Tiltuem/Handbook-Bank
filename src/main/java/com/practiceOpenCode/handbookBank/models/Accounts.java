package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.AccountStatusCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.RegulationAccountTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "accounts")
@SQLDelete(sql = "update accounts set deleted=true where id=?")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accounts")
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

    public Accounts() {
        this.deleted = false;
    }
}
