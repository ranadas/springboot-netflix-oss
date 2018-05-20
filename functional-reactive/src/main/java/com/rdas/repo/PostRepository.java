package com.rdas.repo;

import com.rdas.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findByTitle(String name);

    /*
    Flux<Post> findByName(Mono<String> name);
    Mono<Post> findByNameAndImageUrl(Mono<String> name, String imageUrl);
    @Query("{ 'name': ?0, 'imageUrl': ?1}")
    Mono<Post> findByNameAndImageUrl(String name, String imageUrl);
     */
}
