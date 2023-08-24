package com.practiceOpenCode.handbookBank;



import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Arrays;


@SpringBootApplication
public class DirectoryBankApplication {
    @Bean
    XmlAdapter xmlAdapter() {
        return new XmlAdapter() {
            @Override
            public Object unmarshal(Object o) throws Exception {
                return null;
            }

            @Override
            public Object marshal(Object o) throws Exception {
                return null;
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(DirectoryBankApplication.class, args);
    }

}
