package br.com.gabrieleholanda.fichas_cantina.Controller;


import br.com.gabrieleholanda.fichas_cantina.DTO.FichasResponseDTO;
import br.com.gabrieleholanda.fichas_cantina.Service.FichasService;
import br.com.gabrieleholanda.fichas_cantina.entity.FichasCantina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/carteirinhas")
    @CrossOrigin("*")
    public class FichasController {

        @Autowired
        private FichasService service;

        @GetMapping
        public List<FichasResponseDTO> findAll() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<FichasResponseDTO> findById(@PathVariable Long id) {
            var result = service.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<FichasResponseDTO> cadastrar(@RequestBody FichasCantina carteirinha) {
            var result = service.cadastrar(carteirinha);
            return ResponseEntity.ok(result);
        }

        @PutMapping("/{id}")
        public ResponseEntity<FichasResponseDTO> atualizar(@PathVariable Long id,
                                                                @RequestBody FichasCantina carteirinha) {
            var result = service.atualizar(id, carteirinha);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @DeleteMapping("/{id}/excluir")
        public ResponseEntity<Void> excluir(@PathVariable Long id) {
            var result = service.excluir(id);

            if (result) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @PutMapping("/{id}/bloquear")
        public ResponseEntity<Void> inativar(@PathVariable Long id) {
            var result = service.inativar(id);
            if (result) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @PutMapping("/{id}/desbloquear")
        public ResponseEntity<FichasResponseDTO> ativar(@PathVariable Long id) {
            var result = service.ativar(id);

            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @PostMapping("/{id}/debitar")
        public ResponseEntity<FichasResponseDTO> debitar(@PathVariable Long id,
                                                        @RequestParam Double valor) {
            var result = service.debitarSaldo(id, valor);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/{id}/recarregar")
        public ResponseEntity<FichasResponseDTO> recarregar(@PathVariable Long id,
                                                                 @RequestParam Double valor) {
            var result = service.recarregar(id, valor);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }
