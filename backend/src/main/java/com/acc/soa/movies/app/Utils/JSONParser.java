package com.acc.soa.movies.app.Utils;

import com.acc.soa.movies.app.SOAPEntities.Movie;
import com.acc.soa.movies.app.SOAPEntities.Movies;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.List;

public final class JSONParser {

    public static Movie parseMovieJSON(String json) {
        JSONObject jsonMovie = new JSONObject(json);

        Movie movie = new Movie();
        movie.setMovieId(BigInteger.valueOf(jsonMovie.getInt("id")));
        movie.setTitle(jsonMovie.getString("title"));
        movie.setRating(jsonMovie.getFloat("vote_average"));
        movie.setPhoto(Constants.PHOTO_URL + jsonMovie.getString("poster_path"));
        movie.setTagline(jsonMovie.getString("overview"));
        movie.setYear(BigInteger.valueOf(
                Integer.valueOf(((jsonMovie.getString("release_date")).split("-"))[0])));

        return movie;
    }

    public static Movie parseDetailedMovieJSON(String json) {
        Movie movie = JSONParser.parseMovieJSON(json);
        movie.setGenres(new Movie.Genres());

        JSONObject root = new JSONObject(json);

        JSONArray genres = root.getJSONArray("genres");
        for (int i = 0; i < genres.length(); i++) {
            JSONObject genre = genres.getJSONObject(i);
            movie.getGenres().getGenre().add(genre.getString("name"));
        }

        return movie;
    }

    public static Movies parseMoviesJSON(String json) {
        Movies movies = new Movies();
        List<Movie> movieList = movies.getMovie();
        JSONObject root = new JSONObject(json);
        JSONArray jsonMovies = root.getJSONArray("results");

        for (int i = 0; i < 9; i++) {
            JSONObject jsonMovie = jsonMovies.getJSONObject(i);
            movieList.add(parseMovieJSON(jsonMovie.toString()));
        }

        return movies;
    }
}
