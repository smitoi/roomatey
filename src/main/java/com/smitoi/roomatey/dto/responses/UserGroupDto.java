package com.smitoi.roomatey.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDto {

    public Long groupId;
    public String groupName;
    public String role;
    public String userId;
}
