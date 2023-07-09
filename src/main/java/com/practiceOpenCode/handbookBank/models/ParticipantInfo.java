package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.*;
import com.practiceOpenCode.handbookBank.models.directories.*;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "participant_info")
@Data
@XmlRootElement(namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantInfo")
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "NameP")
    @Column(name = "participant_name", length = 160)
    private String participantName;

    @XmlAttribute(name = "EnglName")
    @Column(name = "english_name", length = 140)
    private String englishName;

    @XmlAttribute(name = "RegN")
    @Column(name = "registration_number", length = 9)
    private String registrationNumber;

    @XmlAttribute(name = "CntrCd")
    @Column(name = "country_code", length = 2)
    private String countryCode;

    @XmlAttribute(name = "Rgn")
    @Column(name = "region", length = 2)
    private String region;

    @XmlAttribute(name = "Ind")
    @Column(name = "index", length = 6)
    private String index;

    @XmlAttribute(name = "Tnp")
    @Column(name = "locality_type", length = 5)
    private String localityType;

    @XmlAttribute(name = "Nnp")
    @Column(name = "locality_name", length = 25)
    private String localityName;

    @XmlAttribute(name = "Adr")
    @Column(name = "address", length = 160)
    private String address;

    @XmlAttribute(name = "PrntBIC")
    @Column(name = "bic_parent")
    private long bicParent;

    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "date_in")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "date_out")
    private LocalDate dateOut;

    @XmlAttribute(name = "PtType")
    @XmlJavaTypeAdapter(ParticipantTypeCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_type_id")
    private ParticipantTypeCode participantTypeCode;

    @XmlAttribute(name = "Srvcs")
    @XmlJavaTypeAdapter(ServiceCsCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_cs_id")
    private ServiceCsCode serviceCsCode;

    @XmlAttribute(name = "XchType")
    @XmlJavaTypeAdapter(ExchangeParticipantCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_participant_id")
    private ExchangeParticipantCode exchangeParticipantCode;

    @XmlAttribute(name = "UID")
    @Column(name = "uid")
    private long uid;

    @XmlAttribute(name = "ParticipantStatus")
    @XmlJavaTypeAdapter(ParticipantStatusCodeAdapter.class)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_status_id")
    private ParticipantStatusCode participantStatusCode;

    @XmlElement(name = "RstrList", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restriction_list_id")
    private List<RestrictionList> restrictionList;
}
