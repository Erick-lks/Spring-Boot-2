package academy.devdojo.caetano.springbootacademy.mapper;


import academy.devdojo.caetano.springbootacademy.domain.Anime;
import academy.devdojo.caetano.springbootacademy.requests.AnimePostRequestBody;
import academy.devdojo.caetano.springbootacademy.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
