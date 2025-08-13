package github.devhub.testyouai.adapter.in.mapper;

import github.devhub.testyouai.adapter.in.dto.OptionResponseDTO;
import github.devhub.testyouai.adapter.in.dto.QuestionResponseDTO;
import github.devhub.testyouai.domain.model.Option;
import github.devhub.testyouai.domain.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OptionMapper.class})
public abstract class QuestionMapper {

    public abstract QuestionResponseDTO toDTO(Question question);
}
