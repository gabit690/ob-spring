package com.gabit.obpractice.repositories;

import com.gabit.obpractice.entities.VideogameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends JpaRepository<VideogameEntity, Long> {
}
