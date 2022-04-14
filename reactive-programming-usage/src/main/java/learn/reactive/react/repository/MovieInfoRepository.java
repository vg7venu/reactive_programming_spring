package learn.reactive.react.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.react.domain.MovieInfo;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

}
