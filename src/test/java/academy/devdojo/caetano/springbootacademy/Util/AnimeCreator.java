package academy.devdojo.caetano.springbootacademy.Util;

import academy.devdojo.caetano.springbootacademy.domain.Anime;

public class AnimeCreator {
    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();

    }
    public static Anime createValidAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();

    }
    public static Anime createValidUpdateAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();

    }


}
