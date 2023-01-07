package com.smitoi.roomatey.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserGroupKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "group_id")
    Long groupId;
}