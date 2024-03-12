package br.com.webflux.javaWebFlux.repository;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import br.com.webflux.javaWebFlux.entity.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private ReactiveMongoTemplate mongoTemplate;
	
	public Mono<User> save(final User user){
		return mongoTemplate.save(user);
	}
	
}