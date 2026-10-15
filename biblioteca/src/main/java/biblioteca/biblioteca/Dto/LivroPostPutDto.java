package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("isbn")
    @NotBlank(message = "isbn nao pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "titulo nao pode ser vazio")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "o id do autor é obrigatorio")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "ano não pode ser nulo")
    @Positive(message = "ano deve ser positivo")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "edicao não pode ser nula")
    @Positive(message = "edicao deve ser positiva")
    private Integer edicao;
}
