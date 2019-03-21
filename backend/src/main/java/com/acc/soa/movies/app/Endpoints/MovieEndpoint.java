package com.acc.soa.movies.app.Endpoints;

import com.acc.soa.movies.app.Repositories.MovieMetaRepository;
import com.acc.soa.movies.app.SOAPEntities.GetMovieByIdRequest;
import com.acc.soa.movies.app.SOAPEntities.GetMovieByIdResponse;
import com.acc.soa.movies.app.SOAPEntities.GetPopularMovieResponse;
import com.acc.soa.movies.app.SOAPEntities.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
        Movies movies = new Movies();
        GetPopularMovieResponse getPopularMovieResponse = new GetPopularMovieResponse();

        //rest call to TMDB to retrieve popular movies
        //transform REST movie to SOAP Movie element
        //populate movies with movie

        getPopularMovieResponse.setMovies(movies);
        return getPopularMovieResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieByIdRequest")
    @ResponsePayload
    GetMovieByIdResponse getMovieByIdResponse(@RequestParam GetMovieByIdRequest getMovieByIdRequest){
        GetMovieByIdResponse getMovieByIdResponse = new GetMovieByIdResponse();

        //rest call to TMDB to retrieve movie based on id
        //parse json
        //instantiate a movie and populate with data
        //place it inside getMovieByIdResponse

        return getMovieByIdResponse;
    }

}
