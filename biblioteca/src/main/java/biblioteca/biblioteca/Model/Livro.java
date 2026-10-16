package biblioteca.biblioteca.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tb_livro")
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_titulo", nullable = false)
    private String titulo;
 
    @Column(name = "ds_isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "nr_ano")
    private Integer ano;

    @Column(name = "nr_edicao")
    private Integer edicao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Autor autor;

} 
