package lera343.hotel.controller;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.ClientResponse;
import lera343.hotel.service.client.impls.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<ClientResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(clientService.getAll());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody ClientRequest client){
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }
    

}
