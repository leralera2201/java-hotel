package lera343.hotel.controller;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Room;
import lera343.hotel.entity.Type;
import lera343.hotel.service.type.impls.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;
    @GetMapping
    public List<TypeResponse> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size){
        return typeService.getAll();
    }
    @GetMapping("/{id}")
    public TypeResponse getById(@PathVariable Long id){
        return typeService.getById(id);
    }

    @PostMapping
    public TypeResponse create(@RequestBody TypeRequest type){
        return typeService.create(type);
    }

    @PutMapping("/{id}")
    public TypeResponse update(@PathVariable Long id, @RequestBody Type type){
        return typeService.update(id, type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        typeService.delete(id);
    }

}
