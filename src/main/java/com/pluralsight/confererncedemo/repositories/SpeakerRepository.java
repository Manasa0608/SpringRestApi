package com.pluralsight.confererncedemo.repositories;

import com.pluralsight.confererncedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
