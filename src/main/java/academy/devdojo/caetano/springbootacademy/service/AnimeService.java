package academy.devdojo.caetano.springbootacademy.service;

import academy.devdojo.caetano.springbootacademy.domain.Anime;
import academy.devdojo.caetano.springbootacademy.exception.BadRequestException;
import academy.devdojo.caetano.springbootacademy.mapper.AnimeMapper;
import academy.devdojo.caetano.springbootacademy.repository.AnimeRepository;
import academy.devdojo.caetano.springbootacademy.requests.AnimePostRequestBody;
import academy.devdojo.caetano.springbootacademy.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimeService {




    private final AnimeRepository animerepository;

    public Page<Anime> listall(Pageable pageable) {
        return animerepository.findAll(pageable);

    }
    public List<Anime> findByName(String name) {
        return animerepository.findByName(name);

    }
    public Anime findByIdOrThrowBadRequestException(long id) {
        return animerepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));


    }


    @Transactional
    public Anime save (AnimePostRequestBody animePostRequestBody) {
        return animerepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));

    }

    public void delete(Long id) {
        animerepository.delete(findByIdOrThrowBadRequestException(id));


    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animerepository.save(anime);


    }
}
