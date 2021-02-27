package lera343.hotel.service.type.interfaces;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Type;

import java.util.List;

public interface ITypeService {
    List<TypeResponse> getAll();
    TypeResponse getById(Long id);
    TypeResponse create(TypeRequest type);
    TypeResponse update(Long id, Type type);
    void delete(Long id);
}
