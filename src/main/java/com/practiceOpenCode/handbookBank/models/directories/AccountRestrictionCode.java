package com.practiceOpenCode.handbookBank.models.directories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_restriction_code")
@Data
@NoArgsConstructor
public class AccountRestrictionCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public AccountRestrictionCode(String code) {
        this.code = code;
    }

    //@Column(name = "description")
    //private String description;
}
