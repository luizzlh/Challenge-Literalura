package br.com.alura.literalura.repository;

import br.com.alura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findAllByAnoDeFalecimentoGreaterThanAndAnoDeNascimentoLessThanEqual(int anoFalecimento, int anoNascimento);

}
