package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class LivroCrudPadraoService implements LivroCrudService {
    @Autowired 
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override 
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
         if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ja existe um livro com este isbn");
        }
        Livro livro = new Livro();
        preencher(livro, dto);
        return paraResponse(livroRepository.save(livro));
    }

      @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return paraResponse(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarPorIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(id);

        livroRepository.findByIsbn(dto.getIsbn())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "ja existe um livro com este isbn");
                });

        preencher(livro, dto);
        return paraResponse(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private void preencher(Livro livro, LivroPostPutDto dto) {
        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());
        livro.setAutor(autor);
    }

    private LivroResponseDto paraResponse(Livro livro) {
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .autorId(livro.getAutor().getId())
                .nomeAutor(livro.getAutor().getNomeAutor())
                .build();
    }
}