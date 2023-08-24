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
@Data
@NoArgsConstructor
public class AccountStatusCode extends AbstractCode{
    @Column(name = "code", unique = true)
    private String code;

    public AccountStatusCode(String code) {
        super();
        this.code = code;
    }
}
