package academy.devdojo.caetano.springbootacademy.client;


import academy.devdojo.caetano.springbootacademy.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.hibernate.mapping.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class,25);
        log.info(entity);

        Anime object = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class,25);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

    //exchange only with MethodExplicit

        ResponseEntity<List> exchange = new RestTemplate().exchange
                ("http://localhost:8080/animes/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List>() {
                });

        log.info(exchange.getBody());

    }
}
