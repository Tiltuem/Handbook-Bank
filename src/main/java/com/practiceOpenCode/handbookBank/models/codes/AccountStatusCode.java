package com.practiceOpenCode.handbookBank.models.codes;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "account_status_codes")
@Data
@NoArgsConstructor
@Component
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
}
