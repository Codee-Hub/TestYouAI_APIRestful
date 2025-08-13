package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.OptionResponseDTO;
import github.devhub.testyouai.adapter.in.dto.TestResponseBasicDTO;
import github.devhub.testyouai.domain.model.Option;
import github.devhub.testyouai.domain.model.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OptionMapper {

    public abstract OptionResponseDTO toDTO(Option Option);

}
