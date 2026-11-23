package br.com.gabrieleholanda.fichas_cantina.Service;

import br.com.gabrieleholanda.fichas_cantina.Controller.FichasController;
import br.com.gabrieleholanda.fichas_cantina.DTO.FichasResponseDTO;
import br.com.gabrieleholanda.fichas_cantina.Repository.FichasRepository;
import br.com.gabrieleholanda.fichas_cantina.entity.FichasCantina;
import br.com.gabrieleholanda.fichas_cantina.enums.StatusCarteirinhaEnum;
import br.com.gabrieleholanda.fichas_cantina.mapper.FichasMapper;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FichasService {

    @Autowired
    private FichasRepository carteirinhaRepository;

    @Autowired
    private FichasMapper carteirinhaMapper;

    public List<FichasResponseDTO> findAll() {
        var result = carteirinhaRepository.findAll();
        return carteirinhaMapper.toRespostaDTOList(result);
    }

    public Optional<FichasResponseDTO> findById(Long id) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {
            return carteirinhaMapper.toResponseDTO(carteirinha);
        });
    }

    public FichasResponseDTO cadastrar(FichasCantina carteirinha) {

        if (carteirinhaRepository.existsByMatricula(carteirinha.getMatricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa matrícula já está cadastrada!");

        }


        carteirinha.setId(null);
        if (carteirinha.getSaldo() == null) {
            carteirinha.setSaldo(0.0);
        }

        carteirinha.setStatus(StatusCarteirinhaEnum.ATIVA);
        var result = carteirinhaRepository.save(carteirinha);
        return carteirinhaMapper.toResponseDTO(result);


    }

    public Optional<FichasResponseDTO> atualizar(Long id, FichasCantina dados) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {

            if (carteirinhaRepository.existsByMatriculaAndIdNot(dados.getMatricula(), id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa matrícula já está cadastrada!");
            }
            carteirinha.setNome(dados.getNome());
            carteirinha.setMatricula(dados.getMatricula());
            var result = carteirinhaRepository.save(carteirinha);
            return carteirinhaMapper.toResponseDTO(result);
        });
    }
    public boolean excluir(Long id) {
        if (carteirinhaRepository.existsById(id)) {
            carteirinhaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean inativar(Long id) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {
            carteirinha.setStatus(StatusCarteirinhaEnum.BLOQUEADA);
            carteirinhaRepository.save(carteirinha);
            return true;
        }).orElse(false);
    }

    public Optional<FichasResponseDTO> ativar(Long id) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {
            carteirinha.setStatus(StatusCarteirinhaEnum.ATIVA);
            var result = carteirinhaRepository.save(carteirinha);
            return carteirinhaMapper.toResponseDTO(result);
        });
    }
    public Optional<FichasResponseDTO> debitarSaldo(Long id, Double valorDebito) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {

            if (carteirinha.getStatus() == StatusCarteirinhaEnum.BLOQUEADA) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carteirinha bloqueada.");
            }

            if (carteirinha.getSaldo() >= valorDebito) {
                carteirinha.setSaldo(carteirinha.getSaldo() - valorDebito);
                var result = carteirinhaRepository.save(carteirinha);
                return carteirinhaMapper.toResponseDTO(result);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente.");
            }
        });
    }

    public Optional<FichasResponseDTO> recarregar(Long id, Double valor) {
        return carteirinhaRepository.findById(id).map(carteirinha -> {
            carteirinha.setSaldo(carteirinha.getSaldo() + valor);
            var result = carteirinhaRepository.save(carteirinha);
            return carteirinhaMapper.toResponseDTO(result);
        });
    }

}