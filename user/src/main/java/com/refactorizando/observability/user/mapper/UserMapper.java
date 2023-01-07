package com.refactorizando.observability.user.mapper;

import com.refactorizando.observability.user.domain.UserDetail;
import com.refactorizando.observability.user.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDto(UserDetail userDetail);

}
