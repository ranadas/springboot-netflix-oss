package com.rdas.repo;

import com.rdas.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryIntegrationTest {

    @Autowired
    PostRepository repository;
    @Autowired
    ReactiveMongoOperations operations;

    @Before
    public void setUp() {
//        operations.collectionExists(Post.class)
//                .flatMap(exists -> exists ? operations.dropCollection(Post.class) : Mono.just(exists))
//                .flatMap(o -> operations.createCollection(Post.class, CollectionOptions.empty()))
//                .then()
//                .block();
//        repository
//                .save(Flux.just(new Product("T Shirt", "Spring Guru printed T Shirt", new BigDecimal(125), "tshirt1.png"),
//                        new Product("T Shirt", "Spring Guru plain T Shirt", new BigDecimal(115), "tshirt2.png"),
//                        new Product("Mug", "Spring Guru printed Mug", new BigDecimal(39), "mug1.png"),
//                        new Product("Cap", "Spring Guru printed Cap", new BigDecimal(66), "cap1.png")))
//                .then()
//                .block();
        repository
                .deleteAll()
                .thenMany(Flux.just("Post Test one", "Post Test two", "Post Test Three", "Post Test four")
                        .flatMap(title -> repository.save(Post.builder().title(title).content("content of " + title).build()))
                )
                .log()
                .subscribe(null, null, () -> log.info("done initialization..."));

    }

    @Test
    public void assertThat_4_Posts_Are_Present() {
        Long count = repository.count().block();
        assertThat(count).isNotNull();
        assertThat(count).isEqualTo(4);

//        List<Post> tShirts = repository.findAll()
//                .collectList()
//                .block();
//        assertThat(tShirts).hasSize(4);
    }

    @Test
    public void assertThat_Post_SearchBy_Id_returnsValidPost() {
        Post post = repository.findAll().blockFirst();
        assertThat(post).isNotNull();
        String title = post.getTitle();


        post = repository.findById(post.getId()).block();
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).containsIgnoringCase(title);
    }


    @Test
    public void findByNameWithMonoQueryTest() {
        List<Post> tShirts = repository.findByTitle("Post Test Three")
                .collectList()
                .block();
        assertThat(tShirts).hasSize(1);
    }
}