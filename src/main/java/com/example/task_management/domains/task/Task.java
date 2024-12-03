package com.example.task_management.domains.task;

import com.example.task_management.domains.user.User;
import com.example.task_management.domains.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tbl_task")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotBlank(message = "Title is mandatory!")
    private String title;
    private String description;

    @NotNull(message = "Status is mandatory!")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "duedate")
    private LocalDateTime dueDate;

    @ManyToOne()
    @JoinColumn(name = "user_uuid", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_uuid", nullable = false)
    private Category category;
}
