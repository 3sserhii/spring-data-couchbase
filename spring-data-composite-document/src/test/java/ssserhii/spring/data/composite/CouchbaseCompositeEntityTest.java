package ssserhii.spring.data.composite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {
                SpringDataCouchbaseCompostiteDocumentApplication.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CouchbaseCompositeEntityTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndGetDocument() {
        User.Child child = new User.Child("Jane", 18);
        String testId = "testId";
        User expectedUser = new User(testId, List.of("John"), List.of(child));

        userRepository.save(expectedUser);

        Optional<User> actualUser = userRepository.findById(testId);

        Assertions.assertThat(actualUser).isEqualTo(expectedUser);
    }
}
