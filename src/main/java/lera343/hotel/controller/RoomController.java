package lera343.hotel.controller;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.entity.Client;
import lera343.hotel.entity.Room;
import lera343.hotel.service.room.impls.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    @GetMapping
    public List<RoomResponse> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size){
        return roomService.getAll();
    }
    @GetMapping("/{id}")
    public RoomResponse getById(@PathVariable Long id){
        return roomService.getById(id);
    }

    @PostMapping
    public RoomResponse create(@RequestBody RoomRequest room){
        return roomService.create(room);
    }

    @PutMapping("/{id}")
    public RoomResponse update(@PathVariable Long id, @RequestBody Room room){
        return roomService.update(id, room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        roomService.delete(id);
    }

    @GetMapping("/{typeId}/rooms")
    public List<RoomResponse> getRoomsByType(@PathVariable Long typeId){
        return roomService.getRoomsByTypeId(typeId);
    }
}
