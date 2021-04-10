package lera343.hotel.controller;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.service.room.impls.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoomResponse> create(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<RoomResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(roomService.getAll());
    }


    @GetMapping("/type/{typeId}")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<RoomResponse>> getRoomsByType(@PathVariable Long typeId){
        return ResponseEntity.ok(roomService.getRoomsByTypeId(typeId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<RoomResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @RequestBody RoomRequest room){
        return ResponseEntity.ok(roomService.update(id, room));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }
}
