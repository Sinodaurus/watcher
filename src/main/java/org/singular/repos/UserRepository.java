package org.singular.repos;

import org.singular.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    public User findByFirstNameAndLastName(String firstName, String lastName);
}
