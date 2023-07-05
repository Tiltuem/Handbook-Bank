package com.practiceOpenCode.handbookBank;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.repositories.MessageRepository;
import com.practiceOpenCode.handbookBank.services.MessageService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class DirectoryBankApplication {

    public static void main(String[] args) throws JAXBException {
        SpringApplication.run(DirectoryBankApplication.class, args);
        unmarshaller();
    }

    public static void unmarshaller() throws JAXBException {
        File file = new File("D:\\20220630_ED807_full.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Message e = (Message) jaxbUnmarshaller.unmarshal(file);
        System.out.println("all good");

    }

}
