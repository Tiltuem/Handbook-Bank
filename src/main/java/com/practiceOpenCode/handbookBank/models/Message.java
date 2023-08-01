package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.CreationReasonCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.InformationTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateTimeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
@Data
@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlAttribute(name = "EDNo")
    @Column(name = "ed_number")
    private long edNumber;

    @XmlAttribute(name = "EDDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "ed_date")
    private LocalDate edDate;

    @XmlAttribute(name = "EDAuthor")
    @Column(name = "ed_author")
    private long edAuthor;

    @XmlAttribute(name = "EDReceiver")
    @Column(name = "ed_receiver")
    private long edReceiver;

    @XmlAttribute(name = "CreationReason")
    @XmlJavaTypeAdapter(CreationReasonCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creation_reason_id")
    private CreationReasonCode creationReasonCode;

    @XmlAttribute(name = "CreationDateTime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @XmlAttribute(name = "InfoTypeCode")
    @XmlJavaTypeAdapter(InformationTypeCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "information_type_code_id")
    private InformationTypeCode informationTypeCode;

    @XmlAttribute(name = "BusinessDay")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "business_day")
    private LocalDate businessDay;

    @XmlAttribute(name = "DirectoryVersion")
    @Column(name = "directory_version")
    private int directoryVersion;

    @XmlElement(name = "BICDirectoryEntry", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_list_id")
    private List<BICDirectoryEntry> bicDirectoryEntryList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fileInfo_id")
    private FileInfo fileInfo;
}

