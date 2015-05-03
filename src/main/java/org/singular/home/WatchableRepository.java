package org.singular.home;

import org.singular.entities.Watchable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchableRepository extends JpaRepository<Watchable, Long> {
}
