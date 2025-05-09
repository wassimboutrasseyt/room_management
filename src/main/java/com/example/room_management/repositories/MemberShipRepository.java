package com.example.room_management.repositories;

import com.example.room_management.entities.Membership;
import com.example.room_management.entities.PrvRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberShipRepository extends JpaRepository<Membership, UUID> {
    List<Membership> findByRoom(PrvRoom prvRoom);
}
