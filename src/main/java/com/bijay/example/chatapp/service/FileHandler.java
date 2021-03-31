package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.model.Document;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:50 AM
 */

@Component
public class FileHandler {

    private IDocumentsApi iDocumentsApi;

    public String multipartSingleNameResolver(MultipartFile file) {
        if (file != null) {
            if (!file.getOriginalFilename().trim().equals("")) {
                return file.getOriginalFilename();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void chatImageHandler(MultipartFile file, Document document) {
        if (!file.isEmpty()) {
            try {
                String filePath;
                if (SystemUtils.IS_OS_LINUX)
                    filePath = "/opt/mbank/chatimage";
                else
                    filePath = "D:/mbank/chatimage";
                File dir = new File(filePath);
                if (!dir.exists()) dir.mkdirs();
                dir = new File(filePath + File.separator + document.getIdentifier());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        filePath + File.separator + document.getIdentifier() + "." + document.getExtention()));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Document saveImage(MultipartFile file, String folder) {
        if (!file.isEmpty()) {
            try {
                Document document = new Document();
                document.setIdentifier(UUID.randomUUID().toString());
                document.setIdentifier(FilenameUtils.getExtension(file.getOriginalFilename()));
                File dir;
                byte[] bytes = file.getBytes();
                String filePath = "";
                if (SystemUtils.IS_OS_LINUX) {
                    filePath = "/opt/mbank/" + folder;
                } else {
                    filePath = "D:/mbank/" + folder;
                }
                dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        filePath + "/" + document.getIdentifier() + "." + document.getExtention()));
                stream.write(bytes);
                stream.close();
                return document;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
