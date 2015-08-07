package org.singular.repos;

import org.singular.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    public Movie findByTitle(String title);
}