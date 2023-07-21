package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceCsCodeService {
    Page<ServiceCsCode> getAllCodes(Pageable pageable);
    void save (ServiceCsCode serviceCsCode);
    void deleteViaId(long id);
    ServiceCsCode getViaId(long id);
}
