package lera343.hotel.controller;

import lera343.hotel.entity.Room;
import lera343.hotel.service.room.impls.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    RoomService roomService;
    @GetMapping
    public List<Room> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer size){
        return roomService.getAll();
    }
    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id){
        return roomService.getById(id);
    }

    @PostMapping
    public Room create(@RequestBody Room room){
        return roomService.create(room);
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @RequestBody Room room){
        return roomService.update(id, room);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        roomService.delete(id);
    }
}
