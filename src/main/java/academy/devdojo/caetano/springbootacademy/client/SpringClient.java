package academy.devdojo.caetano.springbootacademy.client;


import academy.devdojo.caetano.springbootacademy.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 25);
        log.info(entity);

        Anime object = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 25);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        //exchange only with MethodExplicit
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {
                });

        log.info(exchange.getBody());

    /*    Anime kingdom = Anime.builder().name("Kingdom").build();
         Anime kingdomsaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
        log.info("Saved Anime without RequestMethod POST {}", kingdomsaved);

        NEED A METHOD HttpHeaders
*/
        Anime samuraiChampoo = Anime.builder().name("Samurai Champoo2").build();
        ResponseEntity<Anime> samuraiChampoosaved = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChampoo, createJsonHeader()), Anime.class);

        log.info("saved anime {} ", samuraiChampoosaved);

        Anime animeToBeUpdate = samuraiChampoosaved.getBody();
        animeToBeUpdate.setName("Super Eleven");

        ResponseEntity<Void> samuraichampooUpdate = new RestTemplate()
                .exchange("http://localhost:8080/animes", HttpMethod.PUT,
                        new HttpEntity<>(animeToBeUpdate, createJsonHeader()), Void.class);

        log.info(samuraichampooUpdate);


        ResponseEntity<Void> samuraichampooDelete = new RestTemplate()
                .exchange("http://localhost:8080/animes/{id}", HttpMethod.DELETE, null
                        , Void.class, animeToBeUpdate.getId());
        log.info(samuraichampooDelete);

    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}


