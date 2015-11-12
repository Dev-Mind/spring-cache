package com.devmind.cache.repository;


import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test de {@link ArticleRepository}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceTestConfig.class)
public class ArticleRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ArticleRepository articleRepository;

    @Value("${spring.datasource.username}")
    private String test;

    @Before
    public void setUp() throws Exception {
        DbSetup dbSetup = new DbSetup(
                new DataSourceDestination(dataSource),
                sequenceOf(
                        deleteAllFrom("Article", "Author"),
                        sequenceOf(
                                insertInto("Author")
                                        .withGeneratedValue("id", ValueGenerators.sequence())
                                        .columns("lastname", "firstname")
                                        .values("EHRET", "Guillaume")
                                        .build(),
                                insertInto("Article")
                                        .withGeneratedValue("id", ValueGenerators.sequence())
                                        .columns("title", "content", "Author_id")
                                        .values("Spring cache", "Spring helps tou to become an expert in cache", 1)
                                        .build()
                        )
                )
        );
        dbSetup.launch();
    }

    @Test
    public void findAccountByLogin(){
        assertThat(articleRepository.findArticlesWithAuthors()).hasSize(1);
    }
}