package lera343.hotel.service.type.impls;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Room;
import lera343.hotel.entity.Type;
import lera343.hotel.repository.TypeRepository;
import lera343.hotel.service.type.interfaces.ITypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TypeService implements ITypeService {
    private final TypeRepository typeRepository;
    @Override
    public List<TypeResponse> getAll() {
        var types = typeRepository.findAll();
        return types.stream().map(TypeResponse::mapToTypeResponse).collect(Collectors.toList());
    }

    @Override
    public TypeResponse getById(Long id) {
        Optional<Type> result = typeRepository.findById(id);
        if (result.isPresent()) {
            return TypeResponse.mapToTypeResponse(result.get());
        } else {
            return null;
        }
    }

    @Override
    public TypeResponse create(TypeRequest type) {
        var newType = Type.builder()
                .id(new Random().nextLong())
                .description(type.getDescription())
                .name(type.getName())
                .rooms(new HashSet<>())
                .build();

        return TypeResponse.mapToTypeResponse(typeRepository.save(newType));
    }

    @Override
    public TypeResponse update(Long id, Type type) {
        return TypeResponse.mapToTypeResponse(typeRepository.save(type));
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }
}
