package com.bijay.example.chatapp.repository;

import com.bijay.example.chatapp.model.Conversation;
import com.bijay.example.chatapp.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:03 AM
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select msg from Message msg where msg.fromUserId=?1 or msg.toUserId=?1")
    List<Message> findAllByFromUserOrToUser(Long userId);

    @Query("select msg from Message msg where msg.id=?1")
    Message findMessageById(Long messageId);

//    @Query("select msg from Message msg where msg.fromUser.id=?1 and msg.bank.id=?2")
//    List<Message> findMessageByFromUserAndBank(Long userId, Long bankId);

    @Query("select msg from Message msg where msg.conversation.id=?1")
    List<Message> findAllByConversationId(Long convId);

//    @Query("select msg from Message msg where msg.toUser.id=?1 and msg.bank.id=?2")
//    Page<Message> findMessageByToUserAndBank(Long userId, Long bankId, Pageable pageable);

//    @Query("select msg.fromUser from Message msg where msg.bank.id=?1 and msg.id=?2")
//    User findToUserByMessageAndBank(Long bankId, Long msgId);

    @Query("select msg.conversation from Message msg where msg.id=?1")
    Conversation findConversationByMessageId(Long msgId);

    @Query("select msg.fromUserId from Message msg where msg.bankId=?1 and msg.id=?2")
    Long findToUserIdByMessageIdAndBankId(Long bankId, Long msgId);

}
