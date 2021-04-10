package lera343.hotel.controller;

import lera343.hotel.entity.Personnel;
import lera343.hotel.service.personnel.impls.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/personnels", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PersonnelController {
    private final PersonnelService personnelService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Personnel> create(@RequestBody Personnel personnel) {
        return ResponseEntity.ok(personnelService.create(personnel));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<Personnel>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(personnelService.getAll());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Personnel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personnelService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Personnel> update(@PathVariable Long id, @RequestBody Personnel personnel){
        return ResponseEntity.ok(personnelService.update(id, personnel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        personnelService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }

}
