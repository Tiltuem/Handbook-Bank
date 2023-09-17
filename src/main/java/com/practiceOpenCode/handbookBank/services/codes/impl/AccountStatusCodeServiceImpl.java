package com.practiceOpenCode.handbookBank.services.codes.impl;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountStatusCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountStatusCodeServiceImpl extends AbstractCodeServiceImpl<AccountStatusCode, AccountStatusCodeRepository>  {
}
