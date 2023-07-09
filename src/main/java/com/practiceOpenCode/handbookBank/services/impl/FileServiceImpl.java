package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.FileInfo;
import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.services.FileService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String download(String date) throws IOException {
        String fileName = date.replaceAll("-", "") + "ED01OSBR.zip";
        URL url = new URL("https://cbr.ru/vfs/mcirabis/BIKNew/" + date.replaceAll("-", "") + "ED01OSBR.zip");
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(fileName);

        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return fileName;
    }

    @Override
    public File unpack(String nameFileZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(nameFileZip))) {
            ZipEntry entry;
            String name = null;
            while ((entry = zin.getNextEntry()) != null) {
                name = "src/main/resources/storage/" + entry.getName();

                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            return new File(name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            File fileZip = new File(nameFileZip);
            fileZip.delete();
        }
        return null;
    }

    @Override
    public Message unmarshall(File fileXml) {
        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(Message.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Message e = (Message) jaxbUnmarshaller.unmarshal(fileXml);

            return e;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo addFileInfo(File file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setImportDateTime(LocalDateTime.now());
        return fileInfo;
    }

}
