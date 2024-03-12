package br.com.webflux.javaWebFlux.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import br.com.webflux.javaWebFlux.entity.User;
import br.com.webflux.javaWebFlux.model.request.UserRequest;

@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = IGNORE,
		nullValueCheckStrategy =  ALWAYS
)
public interface UserMapper {
	/*
	  Utilized for mapping the object User ignored id
	*/
	
	@Mapping(target = "id", ignore = true)
	User toEntity(final UserRequest request);
}
