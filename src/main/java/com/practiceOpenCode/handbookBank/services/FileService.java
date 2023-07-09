package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.FileInfo;
import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface FileService {
    String download(String date) throws IOException;
    File unpack(String file);
    Message unmarshall(File fileXml);
    FileInfo addFileInfo(File file);
}
