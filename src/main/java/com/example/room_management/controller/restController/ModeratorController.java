package com.example.room_management.controller.restController;


import com.example.room_management.entities.PrvRoom;
import com.example.room_management.services.implementations.ModeratorService;
import com.example.room_management.services.implementations.PrvRoomService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("moderatorRoomManager")
public class ModeratorController {

    private final ModeratorService moderatorService;
    private final PrvRoomService prvRoomService;
    public ModeratorController(ModeratorService moderatorService, PrvRoomService prvRoomService) {
        this.moderatorService = moderatorService;
        this.prvRoomService = prvRoomService;
    }

    @PostMapping("inviteStudents")
    public String inviteStudents(@RequestParam UUID roomId, @RequestParam UUID studentId) {
        return moderatorService.inviteStudents(roomId, studentId);
    }

    @PostMapping("kickMember")
    public String kickMember(@RequestParam UUID roomId, @RequestParam UUID memberId) {
        return moderatorService.kickMember(roomId, memberId);
    }

    @PostMapping("/promoteMember")
    public String promoteMember(@RequestParam UUID roomId, @RequestParam UUID memberId)  {
        return moderatorService.promoteMember(roomId, memberId);
    }

    @PostMapping("/createPrivateRoom")
    public String createPrivateRoom(@RequestParam String roomName ,@RequestParam String description) {
        return "Request has been sent successfully";
    }

    @PostMapping("/updatePrivateRoom")
    public PrvRoom updatePrivateRoom(@RequestParam UUID id, @RequestBody PrvRoom prvRoom) {
        return prvRoomService.updatePrivateRoom(id, prvRoom);
    }

    @PostMapping("/deletePrivateRoom")
    public boolean deletePrivateRoom(@RequestParam UUID id) {
        return prvRoomService.deletePrivateRoom(id);
    }

    @GetMapping("/allPrivateRoomsByUser")
    public List<PrvRoom> allPrivateRoomsByUser(@RequestParam UUID userId) {
        return prvRoomService.getAllPrivateRooms();
    }
}
