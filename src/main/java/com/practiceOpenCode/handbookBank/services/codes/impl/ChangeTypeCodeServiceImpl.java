package com.practiceOpenCode.handbookBank.services.codes.impl;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.codes.ChangeTypeCodeRepository;
import org.springframework.stereotype.Service;


@Service
public class ChangeTypeCodeServiceImpl extends AbstractCodeServiceImpl<ChangeTypeCode, ChangeTypeCodeRepository> {
}