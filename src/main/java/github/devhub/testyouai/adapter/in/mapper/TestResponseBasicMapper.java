package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.TestRequestBasicDTO;
import github.devhub.testyouai.adapter.in.dto.TestResponseBasicDTO;
import github.devhub.testyouai.aplication.service.UserService;
import github.devhub.testyouai.domain.model.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class TestResponseBasicMapper {

    @Autowired
    UserService userService;

    public abstract TestResponseBasicDTO toDTO(Test test);

    @Mapping(target = "user", expression = "java(userService.findById(testRequestBasicDTO.idUser()))" )
    public abstract Test toEntity(TestRequestBasicDTO testRequestBasicDTO);
}

