package com.example.demo.repository;

import com.example.demo.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findBySenderId(Long senderId);
    List<FeedbackEntity> findByTaskId(Long taskId);
    List<FeedbackEntity> findByReceiverIdAndOnaylandiFalse(Long receiverId);
}
