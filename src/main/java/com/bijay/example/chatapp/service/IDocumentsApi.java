package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.model.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:48 AM
 */

public interface IDocumentsApi {

    Document saveDocuments(String filename);

    Document saveDocumentsWithExactName(String filename);

//    Document updateDocuments(String originalFilename, Document document);

    Document save(MultipartFile file, String folder);

}
