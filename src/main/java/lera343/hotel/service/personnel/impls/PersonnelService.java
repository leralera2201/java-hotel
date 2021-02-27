package lera343.hotel.service.personnel.impls;

import lera343.hotel.entity.Personnel;
import lera343.hotel.repository.PersonnelRepository;
import lera343.hotel.service.personnel.interfaces.IPersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonnelService implements IPersonnelService {
    private final PersonnelRepository personnelRepository;
    @Override
    public List<Personnel> getAll() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel getById(Long id) {
        Optional<Personnel> result = personnelRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Personnel create(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public Personnel update(Long id, Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public void delete(Long id) {
        personnelRepository.deleteById(id);
    }
}
