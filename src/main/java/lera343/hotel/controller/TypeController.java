package lera343.hotel.controller;

import lera343.hotel.entity.Room;
import lera343.hotel.entity.Type;
import lera343.hotel.service.type.impls.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    TypeService typeService;
    @GetMapping
    public List<Type> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer size){
        return typeService.getAll();
    }
    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id){
        return typeService.getById(id);
    }

    @PostMapping
    public Type create(@RequestBody Type type){
        return typeService.create(type);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Long id, @RequestBody Type type){
        return typeService.update(id, type);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        typeService.delete(id);
    }

    @GetMapping("/{typeId}/rooms")
    public List<Room> getRoomsByType(@PathVariable Long typeId){
        return null;
    }
}
