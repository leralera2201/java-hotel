package lera343.hotel.service.personnel.interfaces;

import lera343.hotel.entity.Personnel;

import java.util.List;

public interface IPersonnelService {
    List<Personnel> getAll();
    Personnel getById(Long id);
    Personnel create(Personnel personnel);
    Personnel update(Long id, Personnel personnel);
    void delete(Long id);
}
