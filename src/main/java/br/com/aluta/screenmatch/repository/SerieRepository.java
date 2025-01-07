package br.com.aluta.screenmatch.repository;

import br.com.aluta.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long > {
}
