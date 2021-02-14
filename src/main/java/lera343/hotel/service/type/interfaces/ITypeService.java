package lera343.hotel.service.type.interfaces;

import lera343.hotel.entity.Type;

import java.util.List;

public interface ITypeService {
    List<Type> getAll();
    Type getById(Long id);
    Type create(Type type);
    Type update(Long id, Type type);
    void delete(Long id);
}
