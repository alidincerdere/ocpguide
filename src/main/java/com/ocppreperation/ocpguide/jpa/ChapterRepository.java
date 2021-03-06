package com.ocppreperation.ocpguide.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by adere on 18.01.2019.
 */
public interface ChapterRepository extends CrudRepository<Chapter, Long> {

    Chapter findByName(String name);

    Chapter findById(UUID id);
}
