package com.acc.soa.movies.app.Endpoints;

import com.acc.soa.movies.app.Repositories.MovieMetaRepository;
import com.acc.soa.movies.app.SOAPEntities.*;
import com.acc.soa.movies.app.Utils.HTTPManager;
import com.acc.soa.movies.app.Utils.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.context.MessageContext;

@Endpoint
public class MovieEndpoint {

    private static final String NAMESPACE_URI = "http://movies-soa.com/app";

    private MovieMetaRepository movieMetaRepository;

    @Autowired
    public MovieEndpoint(MovieMetaRepository movieMetaRepository) {
        this.movieMetaRepository = movieMetaRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPopularMovieRequest")
    @ResponsePayload
    GetPopularMovieResponse getPopularMovieResponse() {
        Movies movies;
        GetPopularMovieResponse getPopularMovieResponse = new GetPopularMovieResponse();

        movies = JSONParser.parseMoviesJSON(HTTPManager.HTTPRequest("/movie/popular"));

        getPopularMovieResponse.setMovies(movies);
        return getPopularMovieResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieByIdRequest")
    @ResponsePayload
    GetMovieByIdResponse getMovieByIdResponse(@RequestPayload GetMovieByIdRequest getMovieByIdRequest,
                                              MessageContext messageContext) {
        GetMovieByIdResponse getMovieByIdResponse = new GetMovieByIdResponse();

        Movie movie = JSONParser.parseDetailedMovieJSON(HTTPManager.HTTPRequest("/movie/"
                + getMovieByIdRequest.getMovieId().toString()));

        //check if the current user has the given movie saved in the movie_meta db
        System.out.println(messageContext.getProperty("user_id"));

        getMovieByIdResponse.setMovie(movie);
        getMovieByIdResponse.setAdded(false);

        return getMovieByIdResponse;
    }


}
