package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegulationAccountTypeCodeService {
    Page<RegulationAccountTypeCode> getAllCodes(Pageable pageable);
    void save (RegulationAccountTypeCode regulationAccountTypeCode);
    void deleteViaId(long id);
    RegulationAccountTypeCode getViaId(long id);
}
