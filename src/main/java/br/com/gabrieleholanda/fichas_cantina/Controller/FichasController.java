package br.com.gabrieleholanda.fichas_cantina.Controller;


import br.com.gabrieleholanda.fichas_cantina.DTO.FichasResponseDTO;
import br.com.gabrieleholanda.fichas_cantina.Service.FichasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fichas")
@CrossOrigin("*")
public class FichasController {

    @Autowired
    private FichasService fichasService;

    @GetMapping("/carteirinha")
    public List<FichasResponseDTO> findById(){
        return fichasService.findById();
    }

}
