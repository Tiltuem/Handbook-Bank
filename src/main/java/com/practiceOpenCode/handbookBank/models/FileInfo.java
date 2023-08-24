package com.practiceOpenCode.handbookBank.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_info")
@SQLDelete(sql = "update file_info set deleted=true where id=?")
@Data
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDateTime importDateTime;

    private String fileLink;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    private Boolean deleted;
}
