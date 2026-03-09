package radion.ru.srcm.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import radion.ru.srcm.dto.api.PairApiDto;
import radion.ru.srcm.entity.Pair;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PairMapperEntity {
    Pair toEntity(PairApiDto pairApiDto);
    List<Pair> toEntity(List<PairApiDto> pairApiDtos);
}
