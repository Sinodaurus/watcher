package org.singular.request;

import org.singular.entities.MustWatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MustWatchRepository extends JpaRepository<MustWatch, Long> {
}
