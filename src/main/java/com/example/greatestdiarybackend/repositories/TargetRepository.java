package com.example.greatestdiarybackend.repositories;

import com.example.greatestdiarybackend.entities.Target;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetRepository extends CrudRepository<Target, Long> {
    List<Target> findTargetByUserId(Long id);
    Target findTargetByUuid(String uuid);
}
