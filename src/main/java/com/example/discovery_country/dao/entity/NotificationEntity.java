package com.example.discovery_country.dao.entity;

import com.example.discovery_country.dao.entity.auth.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String message;//text
    LocalDateTime date;
    boolean seen;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    User user;

    boolean deleted;


}
