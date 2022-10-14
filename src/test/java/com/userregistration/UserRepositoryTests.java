package com.userregistration;

import com.userregistration.model.User;
import com.userregistration.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author mehul jain
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("meraemail@gmail.com");
        user.setPassword("merapassword1290");
        user.setFirstName("Rakesh");
        user.setLastName("Kumar");
        
        User savedUser = userRepository.save(user);
        
        User existUser = entityManager.find(User.class, savedUser.getId());
        
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
