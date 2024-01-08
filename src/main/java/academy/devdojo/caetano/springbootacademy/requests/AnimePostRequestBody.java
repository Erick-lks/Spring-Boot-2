package academy.devdojo.caetano.springbootacademy.requests;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = " The Anime name cannot be empty")
    private String name;
}
