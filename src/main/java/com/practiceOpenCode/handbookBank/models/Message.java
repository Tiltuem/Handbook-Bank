package com.practiceOpenCode.handbookBank.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date dateOfCreated;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "drafting_date_message")
    private LocalDate draftingDateMessage;

    @Column(name = "id_drafter_message")
    private Long idDrafterMessage;

    @Column(name = "id_recipient_message")
    private Long idRecipientMessage;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "creation_reason_code")
    private CreationReason creationReason;

    @Column(name = "creation_date_message")
    private LocalDateTime creationDateMessage;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "info_type_code")
    private InformationTypeCode informationTypeCode;

    @Column(name = "date_od")
    private LocalDate dateOd;

    @Column(name = "handbook_version")
    private int handbookVersion;

    @Column(name = "matching_sign")
    private boolean matchingSign;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "message")
    private List<Participant> participant = new ArrayList<>();
}

