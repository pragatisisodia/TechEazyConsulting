package com.example.techeazy.user;

import java.util.Arrays;
import java.util.List;

public enum Role {
    STUDENT(Arrays.asList(Permission.SAVE_STUDENT,Permission.GET_ALL_SUBJECTS,Permission.ENROLL_STUDENT)),
    ADMIN(Arrays.asList(Permission.GET_ALL_STUDENTS,Permission.SAVE_SUBJECT));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
