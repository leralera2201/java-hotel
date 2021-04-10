package lera343.hotel.controller;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Room;
import lera343.hotel.entity.Type;
import lera343.hotel.service.type.impls.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {
    private final TypeService typeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TypeResponse> create(@RequestBody TypeRequest request) {
        return ResponseEntity.ok(typeService.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<TypeResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(typeService.getAll());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<TypeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.getById(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TypeResponse> update(@PathVariable Long id, @RequestBody TypeRequest type){
        return ResponseEntity.ok(typeService.update(id, type));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        typeService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }

}
