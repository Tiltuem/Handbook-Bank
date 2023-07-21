package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationTypeCodeService {
    Page<InformationTypeCode> getAllCodes(Pageable pageable);
    void save (InformationTypeCode informationTypeCode);
    void deleteViaId(long id);
    InformationTypeCode getViaId(long id);
}
