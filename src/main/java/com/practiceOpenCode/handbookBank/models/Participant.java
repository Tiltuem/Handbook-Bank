package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "participant_info")
@Data
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "bic")
    private int bic;

    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "participant_name", length = 1000)
    private String participantName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "areaCode")
    private int areaCode;

    @Column(name = "index")
    private int index;

    @Column(name = "locality_type")
    private String localityType;

    @Column(name = "locality_name")
    private String localityName;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "bic_parent")
    private int bicParent;

    @Column(name = "inclusion_date")
    private LocalDate inclusionDate;

    @Column(name = "exclusion_date")
    private LocalDate exclusionDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_type")
    private ParticipantType participantType;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "available_server")
    private AvailableServer availableServer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "exchange_participant")
    private ExchangeParticipant exchangeParticipant;

    @Column(name = "uid")
    private Long uid;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_status")
    private ParticipantStatus participantStatus;

    @OneToMany(mappedBy = "participant")
    private List<Account> account;
}
