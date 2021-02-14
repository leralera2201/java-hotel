package lera343.hotel.repository;
import lera343.hotel.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
