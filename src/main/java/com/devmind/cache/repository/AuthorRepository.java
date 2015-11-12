package com.devmind.cache.repository;

import com.devmind.cache.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
