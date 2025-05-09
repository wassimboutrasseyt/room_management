package com.example.room_management.services.implementations;

import com.example.room_management.entities.Membership;
import com.example.room_management.entities.PrvRoom;
import com.example.room_management.entities.Room;
import com.example.room_management.entities.enums.Role;
import com.example.room_management.repositories.MemberShipRepository;
import com.example.room_management.repositories.PrvRoomRepository;
import com.example.room_management.services.interfaces.ModeratorServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModeratorService implements ModeratorServiceInt {
    private final PrvRoomRepository prvRoomRepository;
    private final MemberShipRepository memberShipRepository;

    public ModeratorService(PrvRoomRepository prvRoomRepository , MemberShipRepository memberShipRepository) {
        this.prvRoomRepository = prvRoomRepository;
        this.memberShipRepository = memberShipRepository;
    }
    @Override
    public String inviteStudents(UUID roomId, UUID studentId) {
        PrvRoom prvRoom = prvRoomRepository.findById(roomId).get();
        Membership membership = new Membership(null,studentId, prvRoom, Role.Member,false,null,null);
        prvRoom.getMemberships().add(membership);
        prvRoomRepository.save(prvRoom);
        return "Student invited";
    }

    @Override
    public String kickMember(UUID roomId, UUID memberId) {
        PrvRoom prvRoom = prvRoomRepository.findById(roomId).get();
        prvRoom.getMemberships().remove(memberId);
        prvRoomRepository.save(prvRoom);
        return "Member kicked";
    }

    @Override
    public String promoteMember(UUID roomId, UUID memberId) {
        PrvRoom prvRoom = prvRoomRepository.findById(roomId).get();
        List<Membership> membership = memberShipRepository.findByRoom(prvRoom);
        for(Membership m : membership) {
            if (m.getId_student().equals(memberId)) {
                m.setRole(Role.Moderator);
                memberShipRepository.save(m);
                break;
            }
        }
        return "Member promoted";
    }
}
