package lera343.hotel.service.type.impls;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Type;
import lera343.hotel.mapper.TypeMapper;
import lera343.hotel.repository.TypeRepository;
import lera343.hotel.service.type.interfaces.ITypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TypeService implements ITypeService {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
    @Override
    public List<TypeResponse> getAll() {
        var types = typeRepository.findAll();
        return types.stream().map(TypeResponse::mapToTypeResponse).collect(Collectors.toList());
    }

    @Override
    public TypeResponse getById(Long id) {
        var type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return TypeResponse.mapToTypeResponse(type);
    }

    @Override
    public TypeResponse create(TypeRequest type) {
        var newType = typeMapper.fromRequest(type);

        return TypeResponse.mapToTypeResponse(typeRepository.save(newType));
    }

    @Override
    public TypeResponse update(Long id, TypeRequest request) {
        var type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        type.setDescription(request.getDescription());
        type.setName(request.getName());
        return TypeResponse.mapToTypeResponse(typeRepository.save(type));
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }
}
