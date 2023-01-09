package com.smitoi.roomatey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smitoi.roomatey.entity.definitions.Ownable;
import com.smitoi.roomatey.exceptions.NotOwnerOfObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`groups`")
public class Group implements Ownable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY)
    private List<Invitation> invitations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY)
    private List<UserGroup> members;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY)
    private List<Event> events;

    @Override
    public boolean isOwnedBy(User user) {
        Optional<UserGroup> membership = this.members
                .stream()
                .filter((UserGroup userGroup) -> Objects.equals(userGroup.getUser().getId(), user.getId()))
                .findFirst();

        return membership.isPresent() && Objects.equals(membership.get().getRole(), UserGroup.ROLE_OWNER);
    }
}
