package br.com.gabrieleholanda.fichas_cantina.Repository;

import br.com.gabrieleholanda.fichas_cantina.entity.FichasCantina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface FichasRepository extends JpaRepository<FichasCantina, Long> {
}
