package com.smitoi.roomatey.entity;

import com.smitoi.roomatey.entity.definitions.Searchable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task implements Searchable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @NotBlank
    @JoinColumn(name = "assigned_to_id", nullable = false)
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotBlank
    @JoinColumn(name = "created_by_id", nullable = false)
    private User creator;

    @Override
    public String[] getSearchableColumns() {
        return new String[]{"description", "category"};
    }
}
