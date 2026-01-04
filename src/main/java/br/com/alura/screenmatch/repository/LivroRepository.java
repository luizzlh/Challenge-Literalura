package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findLivroByIdiomaEquals(List<String> idioma);
}
