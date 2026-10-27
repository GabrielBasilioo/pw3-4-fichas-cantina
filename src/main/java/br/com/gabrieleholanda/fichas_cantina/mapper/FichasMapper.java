package br.com.gabrieleholanda.fichas_cantina.mapper;

import br.com.gabrieleholanda.fichas_cantina.DTO.FichasResponseDTO;
import br.com.gabrieleholanda.fichas_cantina.entity.FichasCantina;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = FichasMapper.class
)

public interface FichasMapper {

    List<FichasResponseDTO> toRespostaDTOList(List<FichasCantina> entities);
}
