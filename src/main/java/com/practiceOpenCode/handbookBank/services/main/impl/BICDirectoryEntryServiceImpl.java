package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.codes.*;
import com.practiceOpenCode.handbookBank.models.main.Accounts;
import com.practiceOpenCode.handbookBank.models.main.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import com.practiceOpenCode.handbookBank.models.main.SWBICs;
import com.practiceOpenCode.handbookBank.repositories.main.BICDirectoryEntryRepository;
import com.practiceOpenCode.handbookBank.services.main.BICDirectoryEntryService;

import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    @Autowired
    private BICDirectoryEntryRepository repository;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private AbstractCodeService<ParticipantTypeCode> participantTypeCodeService;
    @Autowired
    private AbstractCodeService<ServiceCsCode> serviceCsCodeService;
    @Autowired
    private AbstractCodeService<ExchangeParticipantCode> exchangeParticipantCodeService;
    @Autowired
    private AbstractCodeService<ParticipantStatusCode> participantStatusCodeService;
    @Autowired
    private AbstractCodeService<ChangeTypeCode> changeTypeCodeService;

    @Override
    public void updateById(long id, SWBICs swbiCs) {
        BICDirectoryEntry entry = repository.findById(id);
        entry.getSWBICs().add(swbiCs);
        repository.save(entry);
    }

    @Override
    public void updateById(long id, Accounts account) {
        BICDirectoryEntry entry = repository.findById(id);
        entry.getAccounts().add(account);
        repository.save(entry);
    }

    @Override
    public Page<BICDirectoryEntry> getAllEntries(Pageable pageable) {
        return repository.findByDeleted(pageable, false);
    }

    @Override
    public Page<BICDirectoryEntry> searchEntries(Pageable pageable, String value, Boolean showDeleted, String column, String dateFrom, String dateBy) {
        if (!value.equals("") || !Objects.isNull(dateFrom))
            return search(pageable, value, column, showDeleted, dateFrom, dateBy);
        if (showDeleted) return repository.findAll(pageable);
        return repository.findByDeleted(pageable, false);
    }

    @Override
    public void save(BICDirectoryEntry bicDirectoryEntry) {
        repository.save(bicDirectoryEntry);
    }

    @Override
    public void update(BICDirectoryEntry entry, String participantTypeCode, String serviceCsCode, String exchangeParticipantCode, String participantStatusCode, String changeTypeCode) {
        BICDirectoryEntry oldEntry = repository.findById(entry.getId());
        ParticipantInfo info = entry.getParticipantInfo();

        info.setId(oldEntry.getParticipantInfo().getId());
        info.setExchangeParticipantCode(exchangeParticipantCodeService.getByCode(exchangeParticipantCode));
        info.setParticipantStatusCode(participantStatusCodeService.getByCode(participantStatusCode));
        info.setServiceCsCode(serviceCsCodeService.getByCode(serviceCsCode));
        info.setParticipantTypeCode(participantTypeCodeService.getByCode(participantTypeCode));
        info.setDeleted(false);
        info.setRestrictionList(oldEntry.getParticipantInfo().getRestrictionList());

        oldEntry.setBic(entry.getBic());
        oldEntry.setParticipantInfo(info);
        oldEntry.setChangeTypeCode(changeTypeCodeService.getByCode(changeTypeCode));

        repository.save(oldEntry);
    }

    @Override
    public void add(String bic, ParticipantInfo info, String participantTypeCode, String serviceCsCode, String exchangeParticipantCode, String participantStatusCode, String changeTypeCode) {
        BICDirectoryEntry entry = new BICDirectoryEntry();

        //info.setId(entry.getId());
        info.setExchangeParticipantCode(exchangeParticipantCodeService.getByCode(exchangeParticipantCode));
        info.setParticipantStatusCode(participantStatusCodeService.getByCode(participantStatusCode));
        info.setServiceCsCode(serviceCsCodeService.getByCode(serviceCsCode));
        info.setParticipantTypeCode(participantTypeCodeService.getByCode(participantTypeCode));
        info.setDeleted(false);

        entry.setDeleted(false);
        entry.setChangeTypeCode(changeTypeCodeService.getByCode(changeTypeCode));
        entry.setParticipantInfo(info);
        repository.save(entry);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public BICDirectoryEntry getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        BICDirectoryEntry entry = repository.findById(id);
        entry.setDeleted(false);
        repository.save(entry);
    }

    private Page<BICDirectoryEntry> search(Pageable pageable, String value, String column, Boolean deleted, String dateFrom, String dateBy) {
        Query query;
        StringBuilder queryString = new StringBuilder("SELECT a FROM BICDirectoryEntry a WHERE a.");
        if (!value.equals("")) {

            switch (column) {
                case "bic", "participantInfo.bicParent", "participantInfo.uid" -> queryString.append(column + " = ?1");
                default -> {
                    value = "%" + value + "%";
                    queryString.append(column + " LIKE ?1");
                }
            }

            if (!Objects.isNull(deleted))
                queryString.append(" AND a.deleted = false");

            if (!dateFrom.equals("")) {
                queryString.append(" AND a.participantInfo.dateIn BETWEEN ?2 AND ?3");
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, value)
                        .setParameter(2, LocalDate.parse(dateFrom))
                        .setParameter(3, LocalDate.parse(dateBy));
            } else
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, value);
        } else {
            if (!dateFrom.equals("")) {
                queryString.append("participantInfo.dateIn BETWEEN ?1 AND ?2");
                if (!deleted)
                    queryString.append(" AND a.deleted = false");

                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, LocalDate.parse(dateFrom))
                        .setParameter(2, LocalDate.parse(dateBy));
            } else {
                if (!deleted)
                    query = entityManager.createQuery("SELECT a FROM BICDirectoryEntry a WHERE a.deleted = false");
                else
                    query = entityManager.createQuery("SELECT a FROM BICDirectoryEntry a");
            }
        }

        List entries = query.getResultList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), entries.size());
        List<BICDirectoryEntry> pageContent = entries.subList(start, end);
        return new PageImpl<>(pageContent, pageable, entries.size());
    }
}
