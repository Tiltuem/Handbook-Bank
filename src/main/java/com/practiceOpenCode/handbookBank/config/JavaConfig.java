package com.practiceOpenCode.handbookBank.config;

import com.practiceOpenCode.handbookBank.adapters.*;
import com.practiceOpenCode.handbookBank.models.codes.*;
import com.practiceOpenCode.handbookBank.models.main.Message;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

@Configuration
public class JavaConfig {
    @Autowired
    private AbstractCodeRepository<AccountRestrictionCode> repositoryAccountRestrictionCode;
    @Autowired
    private AbstractCodeRepository<AccountStatusCode> repositoryAccountStatusCode;
    @Autowired
    private AbstractCodeRepository<ChangeTypeCode> repositoryChangeTypeCode;
    @Autowired
    private AbstractCodeRepository<CreationReasonCode> repositoryCreationReasonCode;
    @Autowired
    private AbstractCodeRepository<ExchangeParticipantCode> repositoryExchangeParticipantCode;
    @Autowired
    private AbstractCodeRepository<InformationTypeCode> repositoryInformationTypeCode;
    @Autowired
    private AbstractCodeRepository<ParticipantStatusCode> repositoryParticipantStatusCode;
    @Autowired
    private AbstractCodeRepository<ParticipantTypeCode> repositoryParticipantTypeCode;
    @Autowired
    private AbstractCodeRepository<RegulationAccountTypeCode> repositoryRegulationAccountTypeCode;
    @Autowired
    private AbstractCodeRepository<RestrictionCode> repositoryRestrictionCode;
    @Autowired
    private AbstractCodeRepository<ServiceCsCode> repositoryServiceCsCode;
    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("initDB.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);

        return dataSourceInitializer;
    }

    @Bean
    @DependsOn("dataSourceInitializer")
    @SneakyThrows
    public Unmarshaller unmarshaller() {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(Message.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        setAdapter(unmarshaller);

        return unmarshaller;
    }

    private void setAdapter(Unmarshaller unmarshaller) {
        unmarshaller.setAdapter(new AccountRestrictionCodeAdapter(repositoryAccountRestrictionCode));
        unmarshaller.setAdapter(new AccountStatusCodeAdapter(repositoryAccountStatusCode));
        unmarshaller.setAdapter(new CreationReasonCodeAdapter(repositoryCreationReasonCode));
        unmarshaller.setAdapter(new ExchangeParticipantCodeAdapter(repositoryExchangeParticipantCode));
        unmarshaller.setAdapter(new ChangeTypeCodeAdapter(repositoryChangeTypeCode));
        unmarshaller.setAdapter(new InformationTypeCodeAdapter(repositoryInformationTypeCode));
        unmarshaller.setAdapter(new ParticipantStatusCodeAdapter(repositoryParticipantStatusCode));
        unmarshaller.setAdapter(new ParticipantTypeCodeAdapter(repositoryParticipantTypeCode));
        unmarshaller.setAdapter(new RegulationAccountTypeCodeAdapter(repositoryRegulationAccountTypeCode));
        unmarshaller.setAdapter(new RestrictionCodeAdapter(repositoryRestrictionCode));
        unmarshaller.setAdapter(new ServiceCsCodeAdapter(repositoryServiceCsCode));
    }
}
