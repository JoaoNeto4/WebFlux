package br.com.webflux.javaWebFlux.service;

import org.springframework.stereotype.Service;

import br.com.webflux.javaWebFlux.entity.User;
import br.com.webflux.javaWebFlux.mapper.UserMapper;
import br.com.webflux.javaWebFlux.model.request.UserRequest;
import br.com.webflux.javaWebFlux.repository.UserRepository;
import br.com.webflux.javaWebFlux.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	private final UserMapper mapper;
	
	public Mono<User> save(final UserRequest request){
		return repository.save(mapper.toEntity(request));
	}
	
	public Mono<User> findById(final String id){
		return handleNotFound(repository.findById(id), id);
	}
	
	public Flux<User> findAll(){
		return repository.findAll();
	}
	
	//Method PATCH
	public Mono<User> update(final String id, final UserRequest request) {
		return findById(id)
				.map(entity -> mapper.toEntity(request, entity))
				.flatMap(repository::save);
	}
	
	public Mono<User> delete(final String id) {
		return handleNotFound(repository.findAndRemove(id), id);
	}
	
	private <T> Mono<T> handleNotFound(Mono<T> mono, String id) {
		return mono.switchIfEmpty(Mono.error(
				new ObjectNotFoundException(
						String.format("Object not found. ID: %s,  Type: %s", id, User.class.getSimpleName())
				)
		));
	}

}
