package com.acc.soa.movies.app.Endpoints;

import com.acc.soa.movies.app.Entities.MovieMeta;
import com.acc.soa.movies.app.Entities.UserMeta;
import com.acc.soa.movies.app.Repositories.MovieMetaRepository;
import com.acc.soa.movies.app.Repositories.UserMetaRepository;
import com.acc.soa.movies.app.SOAPEntities.*;
import com.acc.soa.movies.app.Utils.HTTPManager;
import com.acc.soa.movies.app.Utils.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Endpoint
public class MovieEndpoint {

    private static final String NAMESPACE_URI = "http://movies-soa.com/app";

    private MovieMetaRepository movieMetaRepository;
    private UserMetaRepository userMetaRepository;

    @Autowired
    public MovieEndpoint(MovieMetaRepository movieMetaRepository, UserMetaRepository userMetaRepository) {
        this.movieMetaRepository = movieMetaRepository;
        this.userMetaRepository = userMetaRepository;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPopularMovieRequest")
    @ResponsePayload
    GetPopularMovieResponse getPopularMovieResponse() {
        Movies movies;
        GetPopularMovieResponse getPopularMovieResponse = new GetPopularMovieResponse();

        movies = JSONParser.parseMoviesJSON(HTTPManager.HTTPRequest(
                new String[]{"/movie/popular"}));

        getPopularMovieResponse.setMovies(movies);
        return getPopularMovieResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieByIdRequest")
    @ResponsePayload
    GetMovieByIdResponse getMovieByIdResponse(@RequestPayload GetMovieByIdRequest getMovieByIdRequest,
                                              MessageContext messageContext) {
        GetMovieByIdResponse getMovieByIdResponse = new GetMovieByIdResponse();

        Movie movie = JSONParser.parseDetailedMovieJSON(HTTPManager.HTTPRequest(
                new String[]{"/movie/" + getMovieByIdRequest.getMovieId().toString()}));

        Optional<UserMeta> currentUser = this.userMetaRepository.findById(Integer.valueOf(
                messageContext.getProperty("user_id").toString()));

        Optional<MovieMeta> opt = movieMetaRepository.findByTnmbAndOwner(
                movie.getMovieId().intValue(), currentUser.get());

        if (opt.isPresent()) {
            getMovieByIdResponse.setAdded(true);
        } else {
            getMovieByIdResponse.setAdded(false);
        }

        getMovieByIdResponse.setMovie(movie);

        return getMovieByIdResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMoviesByNameRequest")
    @ResponsePayload
    GetMoviesByNameResponse getMoviesByNameResponse(
            @RequestPayload GetMoviesByNameRequest getMoviesByNameRequest) {
        GetMoviesByNameResponse getMoviesByNameResponse = new GetMoviesByNameResponse();

        Movies movies = JSONParser.parseMoviesJSON(
                HTTPManager.HTTPRequest(
                        new String[]{"/search/movie", "&query=" + getMoviesByNameRequest.getTitle()}));

        getMoviesByNameResponse.setMovies(movies);
        return getMoviesByNameResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveMovieToCollectionRequest")
    @ResponsePayload
    SaveMovieToCollectionResponse saveMovieToCollectionResponse(
            @RequestPayload SaveMovieToCollectionRequest saveMovieToCollectionRequest,
            MessageContext messageContext) {
        SaveMovieToCollectionResponse saveMovieToCollectionResponse
                = new SaveMovieToCollectionResponse();

        Optional<UserMeta> currentUser = userMetaRepository.findById(
                Integer.valueOf(messageContext.getProperty("user_id").toString()));

        Optional<MovieMeta> savedMovie = movieMetaRepository.findByTnmbAndOwner(
                saveMovieToCollectionRequest.getMovieId().intValue(),
                currentUser.get());

        if (savedMovie.isPresent()) {
            movieMetaRepository.delete(savedMovie.get());
            saveMovieToCollectionResponse.setAdded(false);
            return saveMovieToCollectionResponse;
        }

        Movie movie = JSONParser.parseDetailedMovieJSON(HTTPManager.HTTPRequest(
                new String[]{"/movie/" + saveMovieToCollectionRequest.getMovieId().toString()}));

        movieMetaRepository.save(new MovieMeta(movie.getMovieId().intValue(), currentUser.get(),
                movie.getTitle()));
        saveMovieToCollectionResponse.setAdded(true);

        return saveMovieToCollectionResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonalMoviesRequest")
    @ResponsePayload
    GetPersonalMoviesResponse getPersonalMoviesResponse(MessageContext messageContext) {
        GetPersonalMoviesResponse getPersonalMoviesResponse = new GetPersonalMoviesResponse();

        Optional<UserMeta> currentUser = userMetaRepository.findById(
                Integer.valueOf(messageContext.getProperty("user_id").toString()));

        List<MovieMeta> metaMovies = movieMetaRepository.findAllByOwner(currentUser.get());
        List<MetaMovie> movies = new ArrayList<>();

        for (MovieMeta movie : metaMovies) {
            MetaMovie metaMovie = new MetaMovie();
            metaMovie.setName(movie.getName());
            metaMovie.setTnmb(BigInteger.valueOf((movie.getTnmb())));
            movies.add(metaMovie);
        }

        getPersonalMoviesResponse.getMetaMovie().addAll(movies);
        return getPersonalMoviesResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRecommendationsRequest")
    @ResponsePayload
    GetRecommendationsResponse getRecommendationsResponse(
            @RequestPayload GetRecommendationsRequest getRecommendationsRequest) {
        GetRecommendationsResponse getRecommendationsResponse = new GetRecommendationsResponse();

        Movies movies = JSONParser.parseMoviesJSON(
                HTTPManager.HTTPRequest(new String[]{"/movie/" + getRecommendationsRequest.getMovieId() +
                        "/recommendations"}));

        getRecommendationsResponse.setMovies(movies);
        return getRecommendationsResponse;
    }
}
