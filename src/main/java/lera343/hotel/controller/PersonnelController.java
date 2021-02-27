package lera343.hotel.controller;

import lera343.hotel.entity.Personnel;
import lera343.hotel.service.personnel.impls.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personnels")
public class PersonnelController {
    private final PersonnelService personnelService;
    @GetMapping
    public List<Personnel> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "10") Integer size){
        return personnelService.getAll();
    }
    @GetMapping("/{id}")
    public Personnel getById(@PathVariable Long id){
        return personnelService.getById(id);
    }

    @PostMapping
    public Personnel create(@RequestBody Personnel personnel){
        return personnelService.create(personnel);
    }

    @PutMapping("/{id}")
    public Personnel update(@PathVariable Long id, @RequestBody Personnel personnel){
        return personnelService.update(id, personnel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        personnelService.delete(id);
    }
}
