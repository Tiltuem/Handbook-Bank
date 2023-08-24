package com.practiceOpenCode.handbookBank.models.codes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@MappedSuperclass
@Data
public abstract class AbstractCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "deleted")
    private Boolean deleted;

    public AbstractCode() {
        this.deleted = false;
    }
}
