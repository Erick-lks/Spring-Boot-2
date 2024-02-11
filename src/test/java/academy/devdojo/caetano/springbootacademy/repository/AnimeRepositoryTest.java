package academy.devdojo.caetano.springbootacademy.repository;

import academy.devdojo.caetano.springbootacademy.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for Anime Repository")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save Persists anime when Successful")
    void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when Successful")
    void save_UpdatesAnime_WhenSuccessful() {

        Anime animeToBeSaved = createAnime();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);

        savedAnime.setName("Overlod");
        Anime animeUpdate = this.animeRepository.save(savedAnime);


        Assertions.assertThat(animeUpdate).isNotNull();
        Assertions.assertThat(animeUpdate.getId()).isNotNull();
        Assertions.assertThat(animeUpdate.getName()).isEqualTo(savedAnime.getName());

    }

    @Test
    @DisplayName("Delete remove anime when Successful")
    void Delete_RemovesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime savedAnime = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(savedAnime);

        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }
    @Test
    @DisplayName("Find by Name returns list of anime when Successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime savedAnime = this.animeRepository.save(animeToBeSaved);

         String name = savedAnime.getName();
         List<Anime> animes = this.animeRepository.findByName(name);



        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(savedAnime);

    }

    @Test
    @DisplayName("Find by Name returns empty list of anime when no anime found")
    void findByName_ReturnsEmptyList_WhenAnimeNotFound() {

        List<Anime> animes = this.animeRepository.findByName("xaxa");


        Assertions.assertThat(animes).isEmpty();


    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();

    }

}