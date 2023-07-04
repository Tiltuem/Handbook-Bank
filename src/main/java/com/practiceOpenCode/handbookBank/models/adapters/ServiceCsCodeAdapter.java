package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.ServiceCsCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ServiceCsCodeAdapter extends XmlAdapter<String, ServiceCsCode> {
    public ServiceCsCode unmarshal(String code) throws Exception {
        return new ServiceCsCode(code);
    }
    public String marshal(ServiceCsCode serviceCsCode) throws Exception {
        return serviceCsCode.getCode();
    }
}