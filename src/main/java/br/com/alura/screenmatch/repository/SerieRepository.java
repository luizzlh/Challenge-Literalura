package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.models.Autor;
import br.com.alura.screenmatch.models.Categoria;
import br.com.alura.screenmatch.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Autor> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Autor> findTop5ByOrderByAvaliacaoDesc();

    List<Autor> findByGenero(Categoria categoria);

    List<Autor> findByTotalTemporadasLessThanAndAvaliacaoGreaterThanEqual(Integer totalTemporadas, Double avaliacao);

    @Query("SELECT S FROM Autor S WHERE S.totalTemporadas <= :totalTemporadas AND S.avaliacao >= :avaliacao")
    List<Autor> seriesPorTemporadaEAvaliacao(Integer totalTemporadas, Double avaliacao);

    @Query("SELECT E FROM Autor S JOIN S.episodios E WHERE E.titulo ILIKE %:trechoEpisodio%")
    List<Livro> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT E FROM Autor S JOIN S.episodios E WHERE S = :serie ORDER BY E.avaliacao DESC LIMIT 5")
    List<Livro> topEpisodiosPorSerie(Autor autor);

    @Query("SELECT E FROM Autor S JOIN S.episodios E WHERE S = :serie AND YEAR(E.dataLancamento) >= :anoLancamento")
    List<Livro> episodiosPorSerieEAno(Autor autor, Integer anoLancamento);
}
