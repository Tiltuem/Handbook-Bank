package com.practiceOpenCode.handbookBank.models.directories;



import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_status")
@Data
@NoArgsConstructor
public class AccountStatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public AccountStatusCode(String code) {
        this.code = code;
    }

    //@Column(name = "description")
    //private String description;
}
