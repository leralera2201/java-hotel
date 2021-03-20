package lera343.hotel.repository;
import java.util.Optional;
import lera343.hotel.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findTypeByName(String name);
}
