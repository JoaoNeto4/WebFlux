package br.com.webflux.javaWebFlux.service;

import org.springframework.stereotype.Service;

import br.com.webflux.javaWebFlux.entity.User;
import br.com.webflux.javaWebFlux.mapper.UserMapper;
import br.com.webflux.javaWebFlux.model.request.UserRequest;
import br.com.webflux.javaWebFlux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	private final UserMapper mapper;
	
	public Mono<User> save(final UserRequest request){
		return repository.save(mapper.toEntity(request));
	}

}
