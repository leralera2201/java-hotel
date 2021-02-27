package lera343.hotel.controller;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.ClientResponse;
import lera343.hotel.entity.Client;
import lera343.hotel.service.client.impls.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    public List<ClientResponse> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size){
        return clientService.getAll();
    }
    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id){
        return clientService.getById(id);
    }

    @PostMapping
    public ClientResponse create(@RequestBody ClientRequest client){
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id, @RequestBody Client client){
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }
}
