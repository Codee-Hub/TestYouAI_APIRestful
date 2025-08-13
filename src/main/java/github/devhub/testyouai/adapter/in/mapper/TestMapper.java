package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.TestRequestBasicDTO;
import github.devhub.testyouai.adapter.in.dto.TestResponseBasicDTO;
import github.devhub.testyouai.adapter.in.dto.TestResponseDTO;
import github.devhub.testyouai.aplication.service.UserService;
import github.devhub.testyouai.domain.model.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OptionMapper.class})
public abstract class TestMapper {

    public abstract TestResponseDTO toDTO(Test test);

}
