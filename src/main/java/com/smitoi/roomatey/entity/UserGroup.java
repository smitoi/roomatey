package com.smitoi.roomatey.entity;

import com.smitoi.roomatey.entity.keys.UserGroupKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_groups", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"group_id", "user_id"}),
})
public class UserGroup {

    public static final String ROLE_OWNER = "owner";
    public static final String ROLE_MEMBER = "member";
    public static final String ROLE_GUEST = "guest";


    @EmbeddedId
    private UserGroupKey id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = false, insertable = false, updatable = false)
    private Group group;

    @NotBlank
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "joined_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp joinedAt;
}
