package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    boolean existsByIsbn(String isbn);
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findByIsbnContainingIgnoreCase(String isbn);
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
