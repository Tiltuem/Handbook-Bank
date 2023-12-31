package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class ServiceCsCodeAdapter extends XmlAdapter<String, ServiceCsCode> {
    private final List<ServiceCsCode> serviceCsCodeList;

    public ServiceCsCodeAdapter(AbstractCodeRepository<ServiceCsCode> repository) {
        serviceCsCodeList = repository.findAll();
    }

    @Override
    public ServiceCsCode unmarshal(String code) throws Exception {
        for (ServiceCsCode serviceCsCode : serviceCsCodeList) {
            if (serviceCsCode.getCode().equals(code)) {
                if (!serviceCsCode.getDeleted()) {
                    return serviceCsCode;
                } else {
                    throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
                }
            }
        }

        throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' отсутствует.\nДля получения файла добавьте этот код в ограничения операций по счету");
    }

    @Override
    public String marshal(ServiceCsCode serviceCsCode) throws Exception {
        return serviceCsCode.getCode();
    }
}