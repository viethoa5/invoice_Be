package com.example.invoice.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReturnType {
    private String fullName;
    private String gmail;
    private String position;
    private Integer userId;
    private String consultant;
    private boolean isAdmin;
    private boolean isActive;

    public UserReturnType(User user) {
        this.fullName = user.getFullName();
        this.isAdmin = user.isAdmin();
        this.gmail = user.getGmail();
        this.position = user.getPosition();
        this.userId = user.getUserId();
        this.isActive = user.isActive();
        this.consultant = user.getConsultant();
    }
}
