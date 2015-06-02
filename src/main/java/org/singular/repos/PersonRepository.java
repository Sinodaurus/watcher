package org.singular.repos;

import org.singular.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
    public Person findByFirstNameAndLastName(String firstName, String lastName);
    public Person findByUsername(String username);
}
