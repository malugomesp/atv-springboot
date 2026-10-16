package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "edicao não pode ser nula")
    private Integer edicao;
}
