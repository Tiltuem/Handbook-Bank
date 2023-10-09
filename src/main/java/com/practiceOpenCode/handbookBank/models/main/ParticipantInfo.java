package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.adapters.*;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "participant_info")
@SQLDelete(sql = "update participant_info set deleted=true where id=?")
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantInfo")
@EntityListeners(AuditingEntityListener.class)
@Data
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

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "PtType")
    @XmlJavaTypeAdapter(ParticipantTypeCodeAdapter.class)
    private ParticipantTypeCode participantTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "Srvcs")
    @XmlJavaTypeAdapter(ServiceCsCodeAdapter.class)
    private ServiceCsCode serviceCsCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "XchType")
    @XmlJavaTypeAdapter(ExchangeParticipantCodeAdapter.class)
    private ExchangeParticipantCode exchangeParticipantCode;

    @XmlAttribute(name = "UID")
    @Pattern(regexp = "(\\d{10})|(^$)",
            message = "Ошибка: неверный формат")
    private String uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "ParticipantStatus")
    @XmlJavaTypeAdapter(ParticipantStatusCodeAdapter.class)
    private ParticipantStatusCode participantStatusCode;

    @OneToMany(cascade = CascadeType.PERSIST)
    @XmlElement(name = "RstrList", namespace = "urn:cbr-ru:ed:v2.0")
    private List<RestrictionList> restrictionList;

    private Boolean deleted;

    public ParticipantInfo() {
        this.deleted = false;
    }
}
