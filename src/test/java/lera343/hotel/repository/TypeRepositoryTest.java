package lera343.hotel.repository;

import lera343.hotel.entity.Type;
import lera343.hotel.stubs.TypeStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TypeRepositoryTest {
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Type expectedType;

    @BeforeEach
    void setUp() {
        expectedType = TypeStub.getRandomType();
        entityManager.persist(expectedType);
        entityManager.flush();
    }

    @Test
    void testFindTypeByName() {
        var actualType = typeRepository.findTypeByName(TypeStub.NAME);
        Assertions.assertThat(actualType.get()).isEqualTo(expectedType);
    }

    @Test
    void testFailedFindTypeByName() {
        var actualType = typeRepository.findTypeByName(TypeStub.NAME + "la");
        Assertions.assertThat(actualType.isPresent()).isEqualTo(false);
    }

}