package academy.devdojo.caetano.springbootacademy.repository;

import academy.devdojo.caetano.springbootacademy.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime , Long> {

    List<Anime> findByName(String name);
}
