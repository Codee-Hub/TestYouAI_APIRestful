package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.UserRequestDTO;
import github.devhub.testyouai.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toDTO(User user);

    default User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}
