package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.adapters.*;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundFileXmlException;
import com.practiceOpenCode.handbookBank.exceptions.UnmarshalXmlException;
import com.practiceOpenCode.handbookBank.models.codes.*;
import com.practiceOpenCode.handbookBank.models.main.FileInfo;
import com.practiceOpenCode.handbookBank.models.main.Message;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.main.FileRepository;
import com.practiceOpenCode.handbookBank.services.main.FileService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository repository;
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


    private static final String START_URL_DOWNLOAD = "https://cbr.ru/vfs/mcirabis/BIKNew/";
    private static final String END_URL_DOWNLOAD = "ED01OSBR.zip";
    private static final String PATH_TO_STORAGE = "src/main/resources/storage/";
    private static final String START_LINK = "file:///";

    @Override
    public String download(String date) {
        String fileName = date.replaceAll("-", "") + END_URL_DOWNLOAD;

        try (ReadableByteChannel channel = Channels.newChannel(new URL(START_URL_DOWNLOAD + fileName).openStream());
             FileOutputStream fos = new FileOutputStream(fileName))
        {
            fos.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            return fileName;
        } catch (IOException e) {
            log.warn("Ошибка при добавлении файла: нет данных за данный день");
            throw new NotFoundFileXmlException("Ошибка: нет данных за данный день");
        }
    }

    @Override
    public File unpack(String nameFileZip) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(nameFileZip))) {
            ZipEntry entry;
            String pathToFile;

            if (!Objects.isNull(entry = zin.getNextEntry())&&entry.getName().endsWith(".xml")) {
                pathToFile = PATH_TO_STORAGE + entry.getName();

                FileOutputStream fout = new FileOutputStream(pathToFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                fout.close();
                zin.closeEntry();
                return new File(pathToFile);
            } else {
                log.warn("Ошибка при добавлении файла: файл XML отсутствует");
                throw new NotFoundFileXmlException("Ошибка: файл XML отсутствует");
            }
        } catch (Exception e) {
            log.error("Ошибка при добавлении файла на стороне сервера");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message unmarshall(File fileXml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            setAdapter(unmarshaller);
            return (Message) unmarshaller.unmarshal(fileXml);
        } catch (Exception e) {
            log.warn("Ошибка при добавлении файла: неверная структура XML");
            throw new UnmarshalXmlException("Ошибка: неверная структура XML");
        }
    }

    @Override
    public FileInfo addFileInfo(File file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setImportDateTime(LocalDateTime.now());
        fileInfo.setFileLink(START_LINK + file.getAbsolutePath());
        return fileInfo;
    }

    @Override
    public Page<FileInfo> getAllFiles(Pageable pageable) {
        return repository.findAllByDeleted(pageable, false);
    }

    @Override
    public void save(FileInfo fileInfo) {
        repository.save(fileInfo);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public FileInfo getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Boolean checkFileExist(String name) {
        return repository.findByName(name) != null;
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
