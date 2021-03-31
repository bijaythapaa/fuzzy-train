package com.bijay.example.chatapp.repository;

import com.bijay.example.chatapp.model.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:06 AM
 */

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Conversation getConversationById(Long id);

    @Query("select con from Conversation con where con.userId=?1 and con.bankId=?2")
    Conversation findConversationByUserIdAndBankId(Long userId, Long bankId);

    @Query("select con from Conversation con where con.bankId=?1")
    Page<Conversation> findAllByBank(Long bankId, Pageable pageable);

//    @Query("select con from Conversation con where con.user.id=?1 and con.bank.id=?2")
//    List<Conversation> findAllByUserAndBank(Long userId, Long BankId);

//    @Query("select con.user from Conversation con where con.id=?1")
//    User findUserByConversationId(Long convId);
}
