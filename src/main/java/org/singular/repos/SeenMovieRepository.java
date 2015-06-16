package org.singular.repos;

import org.singular.entities.SeenMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeenMovieRepository extends JpaRepository<SeenMovie, Long> {
    public SeenMovie findByImdbMovieId(String imdbMovieId);
}
