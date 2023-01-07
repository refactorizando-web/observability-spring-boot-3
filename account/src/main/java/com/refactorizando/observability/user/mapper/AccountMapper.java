package com.refactorizando.observability.user.mapper;

import com.refactorizando.observability.user.domain.AccountDetail;
import com.refactorizando.observability.user.dto.Account;
import com.refactorizando.observability.user.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toDto(AccountDetail accountDetail);

}
