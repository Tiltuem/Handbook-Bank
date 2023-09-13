package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.models.adapters.*;
import com.practiceOpenCode.handbookBank.models.codes.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "participant_info")
@SQLDelete(sql = "update participant_info set deleted=true where id=?")
@Data
@AllArgsConstructor
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantInfo")
@EntityListeners(AuditingEntityListener.class)
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "NameP")
    @Size(max = 160, message = "Ошибка: превышена максимальная длина(160)")
    @NotBlank(message = "Ошибка: заполните поле")
    private String participantName;

    @XmlAttribute(name = "EnglName")
    @Size(max = 140, message = "Ошибка: превышена максимальная длина(140)")
    private String englishName;

    @XmlAttribute(name = "RegN")
    @Size(max = 9, message = "Ошибка: превышена максимальная длина(9)")
    private String registrationNumber;

    @XmlAttribute(name = "CntrCd")
    @Pattern(regexp = "([a-zA-Z]{2})|(^$)",
            message = "Ошибка: неверный формат")
    private String countryCode;

    @XmlAttribute(name = "Rgn")
    @Size(max = 2, message = "Ошибка: превышена максимальная длина(2)")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    private String region;

    @XmlAttribute(name = "Ind")
    @Size(max = 6, message = "Ошибка: превышена максимальная длина(6)")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    private String index;

    @XmlAttribute(name = "Tnp")
    @Size(max = 5, message = "Ошибка: превышена максимальная длина(5)")
    private String localityType;

    @XmlAttribute(name = "Nnp")
    @Size(max = 25, message = "Ошибка: превышена максимальная длина(25)")
    private String localityName;

    @XmlAttribute(name = "Adr")
    @Size(max = 160, message = "Ошибка: превышена максимальная длина(160)")
    private String address;

    @XmlAttribute(name = "PrntBIC")
    @Pattern(regexp = "(\\d{9})|(^$)",
            message = "Ошибка: неверный формат")
    private String bicParent;

    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "Ошибка: поле не может быть пустым")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @XmlAttribute(name = "PtType")
    @XmlJavaTypeAdapter(ParticipantTypeCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_type_id")
    private ParticipantTypeCode participantTypeCode;

    @XmlAttribute(name = "Srvcs")
    @XmlJavaTypeAdapter(ServiceCsCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_cs_id")
    private ServiceCsCode serviceCsCode;

    @XmlAttribute(name = "XchType")
    @XmlJavaTypeAdapter(ExchangeParticipantCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_participant_id")
    private ExchangeParticipantCode exchangeParticipantCode;

    @XmlAttribute(name = "UID")
    @Pattern(regexp = "(\\d{10})|(^$)",
            message = "Ошибка: неверный формат")
    private String uid;

    @XmlAttribute(name = "ParticipantStatus")
    @XmlJavaTypeAdapter(ParticipantStatusCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_status_id")
    private ParticipantStatusCode participantStatusCode;

    @XmlElement(name = "RstrList", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restriction_list_id")
    private List<RestrictionList> restrictionList;

    private Boolean deleted;
    public ParticipantInfo() {
        this.deleted = false;
    }
}
