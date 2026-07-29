package com.edubridge.identity.repositories;

import com.edubridge.identity.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(UUID userId);

    List<Notification> findByUserIdAndStatusOrderByCreatedAtDesc(UUID userId, String status);

    List<Notification> findByUserIdAndTypeOrderByCreatedAtDesc(UUID userId, String type);

    long countByUserIdAndStatus(UUID userId, String status);

    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.readAt IS NULL ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(@Param("userId") UUID userId);
}
