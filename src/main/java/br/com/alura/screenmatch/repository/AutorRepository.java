package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findAllByAnoDeFalecimentoGreaterThanAndAnoDeNascimentoLessThanEqual(int anoFalecimento, int anoNascimento);

}
