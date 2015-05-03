package org.singular.repos;

import org.singular.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchableRepository extends JpaRepository<Movie, Long> {
}
