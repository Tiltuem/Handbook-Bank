package com.practiceOpenCode.handbookBank.models;

import com.practiceOpenCode.handbookBank.models.adapters.CreationReasonCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.InformationTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.models.adapters.LocalDateTimeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
@Data
@SQLDelete(sql = "update messages set deleted=true where id=?")
@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "EDNo")
    @Size(max = 9, message = "Ошибка: превышена максимальная длина(9)")
    @Pattern(regexp = "(\\d*)",
            message = "Ошибка: неверный формат")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String edNumber;

    @XmlAttribute(name = "EDDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(unique = true)
    private LocalDate edDate;

    @XmlAttribute(name = "EDAuthor")
    @Size(max = 10, message = "Ошибка: превышена максимальная длина(10)")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String edAuthor;

    @XmlAttribute(name = "EDReceiver")
    @Size(max = 10, min = 10, message = "Ошибка: введите 10 символов")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    //@NotBlank(message = "Ошибка: поле не может быть пустым")
    private String edReceiver;

    @XmlAttribute(name = "CreationReason")
    @XmlJavaTypeAdapter(CreationReasonCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creation_reason_code_id")
    private CreationReasonCode creationReasonCode;

    @XmlAttribute(name = "CreationDateTime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDateTime;

    @XmlAttribute(name = "InfoTypeCode")
    @XmlJavaTypeAdapter(InformationTypeCodeAdapter.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "information_type_code_id")
    private InformationTypeCode informationTypeCode;

    @XmlAttribute(name = "BusinessDay")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate businessDay;

    @XmlAttribute(name = "DirectoryVersion")
    @Size(max = 2, message = "Ошибка: превышена максимальная длина(2)")
    @Pattern(regexp = "\\d*",
            message = "Ошибка: неверный формат")
    @NotBlank(message = "Ошибка: поле не может быть пустым")
    private String directoryVersion;

    @XmlElement(name = "BICDirectoryEntry", namespace = "urn:cbr-ru:ed:v2.0")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_list_id")
    private List<BICDirectoryEntry> bicDirectoryEntryList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fileInfo_id")
    private FileInfo fileInfo;

    private Boolean deleted;
}

