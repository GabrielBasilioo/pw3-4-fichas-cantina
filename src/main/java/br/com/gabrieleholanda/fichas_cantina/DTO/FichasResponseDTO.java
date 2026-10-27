package br.com.gabrieleholanda.fichas_cantina.DTO;

import br.com.gabrieleholanda.fichas_cantina.enums.StatusCarteirinhaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record FichasResponseDTO(
        Long id,
        String nome,
        String matricula,
        Double saldo,
        StatusCarteirinhaEnum status
){}
