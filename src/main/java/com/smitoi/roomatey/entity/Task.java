package com.smitoi.roomatey.entity;

import com.smitoi.roomatey.entity.definitions.Ownable;
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
public class Task implements Searchable, Ownable {

    public static final String STATUS_PENDING = "pending";

    public static final String STATUS_STARTED = "started";

    public static final String STATUS_FINISHED = "finished";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_id", nullable = false)
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Override
    public String[] getSearchableColumns() {
        return new String[]{"description", "category"};
    }

    @Override
    public boolean isOwnedBy(User user) {
        return creator.getId().equals(user.getId()) || assignee.getId().equals(user.getId());
    }
}
