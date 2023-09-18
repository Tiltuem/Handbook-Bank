package com.practiceOpenCode.handbookBank.models.main;


import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Message message;

    private Boolean deleted;

    public FileInfo() {
        this.deleted = false;
    }
}
