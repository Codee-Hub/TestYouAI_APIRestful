package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.UserRequestDTO;
import github.devhub.testyouai.adapter.in.dto.UserResponseDTO;
import github.devhub.testyouai.domain.model.UserApp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TestBasicMapper.class)
public interface UserMapper {

    UserResponseDTO toDTO(UserApp userApp);

    UserApp toEntity(UserRequestDTO userRequestDTO);

}
