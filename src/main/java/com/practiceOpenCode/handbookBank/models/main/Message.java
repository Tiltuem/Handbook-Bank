package com.practiceOpenCode.handbookBank.models.main;

import com.practiceOpenCode.handbookBank.adapters.CreationReasonCodeAdapter;
import com.practiceOpenCode.handbookBank.adapters.InformationTypeCodeAdapter;
import com.practiceOpenCode.handbookBank.adapters.LocalDateAdapter;
import com.practiceOpenCode.handbookBank.adapters.LocalDateTimeAdapter;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import com.practiceOpenCode.handbookBank.models.security.User;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "messages")
@SQLDelete(sql = "update messages set deleted=true where id=?")
@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
@Data
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

    @Column(unique = true)
    @XmlAttribute(name = "EDDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "CreationReason")
    @XmlJavaTypeAdapter(CreationReasonCodeAdapter.class)
    private CreationReasonCode creationReasonCode;

    @XmlAttribute(name = "CreationDateTime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlAttribute(name = "InfoTypeCode")
    @XmlJavaTypeAdapter(InformationTypeCodeAdapter.class)
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

    @OneToMany(cascade = CascadeType.PERSIST)
    @XmlElement(name = "BICDirectoryEntry", namespace = "urn:cbr-ru:ed:v2.0")
    private List<BICDirectoryEntry> bicDirectoryEntryList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private FileInfo fileInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean deleted;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}

