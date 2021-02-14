package lera343.hotel.controller;

import lera343.hotel.entity.Client;
import lera343.hotel.service.client.impls.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    ClientService clientService;
    @GetMapping
    public List<Client> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer size){
        return clientService.getAll();
    }
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return clientService.getById(id);
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client){
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        clientService.delete(id);
    }

    @GetMapping("/{clientId}/rooms")
    public List<Client> getBookingsByClient(@PathVariable Long clientId){
        return null;
    }
}
