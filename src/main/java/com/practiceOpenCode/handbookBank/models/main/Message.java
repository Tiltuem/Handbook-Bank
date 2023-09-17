package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.adapters.CreationReasonCodeAdapter;
import com.practiceOpenCode.handbookBank.adapters.InformationTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.adapters.LocalDateTimeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.practiceOpenCode.handbookBank.models.security.User;
import lombok.Data;

import javax.persistence.*;
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
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bic_directory_entry_list_id")
    private List<BICDirectoryEntry> bicDirectoryEntryList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fileInfo_id")
    private FileInfo fileInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean deleted;
}

