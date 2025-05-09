package com.example.room_management.services.interfaces;

import java.util.UUID;

public interface ModeratorServiceInt {
    String inviteStudents(UUID roomId, UUID studentId);
    String kickMember(UUID roomId, UUID memberId);
    String promoteMember(UUID roomId, UUID memberId);
}
