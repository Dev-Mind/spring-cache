package com.devmind.cache.repository;

import com.devmind.cache.model.Article;
import com.devmind.cache.model.Author;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query(value = "SELECT a FROM Article a left join fetch a.author where a.id=:id")
    Article findArticleWithAuthors(@Param("id") Long  id);

    @Query(value = "SELECT a FROM Article a left join fetch a.author")
    List<Article> findArticlesWithAuthors();
}
