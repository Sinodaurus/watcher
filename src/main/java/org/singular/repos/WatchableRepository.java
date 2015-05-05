package org.singular.repos;

import org.singular.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WatchableRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m JOIN FETCH m.users WHERE m.watchableId = (:id)")
    public Movie findByIdAndFetchRolesEagerly(@Param("id") Long id);
}
