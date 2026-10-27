package br.com.gabrieleholanda.fichas_cantina.Service;

import br.com.gabrieleholanda.fichas_cantina.Controller.FichasController;
import br.com.gabrieleholanda.fichas_cantina.DTO.FichasResponseDTO;
import br.com.gabrieleholanda.fichas_cantina.Repository.FichasRepository;
import br.com.gabrieleholanda.fichas_cantina.enums.StatusCarteirinhaEnum;
import br.com.gabrieleholanda.fichas_cantina.mapper.FichasMapper;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichasService {

    @Autowired
    private FichasRepository fichasRepository;

    @Autowired
    private FichasMapper fichasMapper;

    public List<FichasResponseDTO> findById

    public debitarSaldo() {

    }

    public List<FichasResponseDTO> findById() {
        var result = fichasRepository.findByCarteirinhaBloqueadaOuAtiva(StatusCarteirinhaEnum.B){

        }
    }
}