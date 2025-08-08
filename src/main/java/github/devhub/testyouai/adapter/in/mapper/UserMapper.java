package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.UserRequestDTO;
import github.devhub.testyouai.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toDTO(User user);

    User toEntity(UserRequestDTO userRequestDTO);
}
