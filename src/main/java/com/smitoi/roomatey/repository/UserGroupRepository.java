package com.smitoi.roomatey.repository;

import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByGroupAndUser(Group group, User user);

    List<UserGroup> findAllByUser(User user);
}
