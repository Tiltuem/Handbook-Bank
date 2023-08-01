package com.practiceOpenCode.handbookBank.models.codes;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "account_status_codes")
@SQLDelete(sql = "update account_status_codes set deleted=true where id=?")
@Where(clause = "deleted = false")
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

    @Column(name = "deleted")
    private Boolean deleted;

    public AccountStatusCode(String code) {
        this.code = code;
        deleted = false;
    }
}
