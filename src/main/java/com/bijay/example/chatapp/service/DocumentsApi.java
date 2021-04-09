package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.model.Document;
import com.bijay.example.chatapp.repository.DocumentRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 2:31 PM
 */

@Service
public class DocumentsApi implements IDocumentsApi {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileHandler fileHandler;

    @Override
    public Document saveDocuments(String filename) {
        Document document = new Document();
        document.setExtention(FilenameUtils.getExtension(filename));
        document.setIdentifier("" + System.currentTimeMillis() + UUID.randomUUID());
        return documentRepository.save(document);
    }

    @Override
    public Document saveDocumentsWithExactName(String filename) {
        Document document = new Document();
        document.setExtention(FilenameUtils.getExtension(filename));
        document.setIdentifier(filename.split("\\.")[0]);
        return documentRepository.save(document);
    }

//    bug in .findOne() method
//    @Override
//    public Document updateDocuments(String filename, Document document) {
//        if (document == null) document = new Document();
//        else document = documentRepository.findOne(document.getId());
//        document.setExtention(FilenameUtils.getExtension(filename));
//        document.setIdentifier("" + System.currentTimeMillis());
//        return documentRepository.save(document);
//    }

    @Override
    public Document save(MultipartFile file, String folder) {
        Document document = fileHandler.saveImage(file, folder);
        if (document != null) {
            return documentRepository.save(document);
        }
        return null;
    }

}
