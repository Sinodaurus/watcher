package org.singular.repos;

import org.singular.entities.SeenMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeenMoviesRepository extends JpaRepository<SeenMovie, Long> {
}
