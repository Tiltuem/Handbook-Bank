package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ServiceCsCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ServiceCsCodeAdapter extends XmlAdapter<String, ServiceCsCode> {
    private final List<ServiceCsCode> serviceCsCodeList;
    private final ApplicationContext ctx;

    public ServiceCsCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        serviceCsCodeList = ctx.getBean(ServiceCsCodeRepository.class).findAll();
    }

    @Override
    public ServiceCsCode unmarshal(String code) throws Exception {
        for (ServiceCsCode serviceCsCode : serviceCsCodeList) {
            if (serviceCsCode.getCode().equals(code)) {
                if(!serviceCsCode.getDeleted())  return serviceCsCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new ServiceCsCode(code);
    }

    @Override
    public String marshal(ServiceCsCode serviceCsCode) throws Exception {
        return serviceCsCode.getCode();
    }
}