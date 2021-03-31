package com.bijay.example.chatapp.repository;

import com.bijay.example.chatapp.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 2:32 PM
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select d from Document d where d.id=?1")
    Optional<Document> findById(Long id);

}
