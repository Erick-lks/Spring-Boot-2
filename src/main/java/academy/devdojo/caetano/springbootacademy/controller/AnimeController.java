package academy.devdojo.caetano.springbootacademy.controller;


import academy.devdojo.caetano.springbootacademy.domain.Anime;
import academy.devdojo.caetano.springbootacademy.requests.AnimePostRequestBody;
import academy.devdojo.caetano.springbootacademy.requests.AnimePutRequestBody;
import academy.devdojo.caetano.springbootacademy.service.AnimeService;
import academy.devdojo.caetano.springbootacademy.util.DataUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {


    private final AnimeService animeService;


    @GetMapping

    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        return ResponseEntity.ok(animeService.listall(pageable));
    }
    @GetMapping(path = "/all")

    public ResponseEntity<List<Anime>>listAll() {
        return ResponseEntity.ok(animeService.listallNomPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }


    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody AnimePutRequestBody) {
        animeService.replace(AnimePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
