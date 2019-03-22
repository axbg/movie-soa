//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.22 at 12:23:59 PM EET 
//


package com.acc.soa.movies.app.SOAPEntities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.acc.soa.movies.app.SOAPEntities package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonalMoviesRequest_QNAME = new QName("http://movies-soa.com/app", "getPersonalMoviesRequest");
    private final static QName _GetPopularMovieRequest_QNAME = new QName("http://movies-soa.com/app", "getPopularMovieRequest");
    private final static QName _User_QNAME = new QName("http://movies-soa.com/app", "user");
    private final static QName _AuthenticatedUser_QNAME = new QName("http://movies-soa.com/app", "authenticatedUser");
    private final static QName _Movie_QNAME = new QName("http://movies-soa.com/app", "movie");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.acc.soa.movies.app.SOAPEntities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Movie }
     * 
     */
    public Movie createMovie() {
        return new Movie();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link AuthenticatedUser }
     * 
     */
    public AuthenticatedUser createAuthenticatedUser() {
        return new AuthenticatedUser();
    }

    /**
     * Create an instance of {@link LoginRequest }
     * 
     */
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetPopularMovieResponse }
     * 
     */
    public GetPopularMovieResponse createGetPopularMovieResponse() {
        return new GetPopularMovieResponse();
    }

    /**
     * Create an instance of {@link Movies }
     * 
     */
    public Movies createMovies() {
        return new Movies();
    }

    /**
     * Create an instance of {@link GetMoviesByNameRequest }
     * 
     */
    public GetMoviesByNameRequest createGetMoviesByNameRequest() {
        return new GetMoviesByNameRequest();
    }

    /**
     * Create an instance of {@link SaveMovieToCollectionRequest }
     * 
     */
    public SaveMovieToCollectionRequest createSaveMovieToCollectionRequest() {
        return new SaveMovieToCollectionRequest();
    }

    /**
     * Create an instance of {@link SaveMovieToCollectionResponse }
     * 
     */
    public SaveMovieToCollectionResponse createSaveMovieToCollectionResponse() {
        return new SaveMovieToCollectionResponse();
    }

    /**
     * Create an instance of {@link GetMoviesByNameResponse }
     * 
     */
    public GetMoviesByNameResponse createGetMoviesByNameResponse() {
        return new GetMoviesByNameResponse();
    }

    /**
     * Create an instance of {@link GetPersonalMoviesResponse }
     * 
     */
    public GetPersonalMoviesResponse createGetPersonalMoviesResponse() {
        return new GetPersonalMoviesResponse();
    }

    /**
     * Create an instance of {@link GetMovieByIdRequest }
     * 
     */
    public GetMovieByIdRequest createGetMovieByIdRequest() {
        return new GetMovieByIdRequest();
    }

    /**
     * Create an instance of {@link GetMovieByIdResponse }
     * 
     */
    public GetMovieByIdResponse createGetMovieByIdResponse() {
        return new GetMovieByIdResponse();
    }

    /**
     * Create an instance of {@link Movie.Genres }
     * 
     */
    public Movie.Genres createMovieGenres() {
        return new Movie.Genres();
    }

    /**
     * Create an instance of {@link Movie.Actors }
     * 
     */
    public Movie.Actors createMovieActors() {
        return new Movie.Actors();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://movies-soa.com/app", name = "getPersonalMoviesRequest")
    public JAXBElement<Object> createGetPersonalMoviesRequest(Object value) {
        return new JAXBElement<Object>(_GetPersonalMoviesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://movies-soa.com/app", name = "getPopularMovieRequest")
    public JAXBElement<Object> createGetPopularMovieRequest(Object value) {
        return new JAXBElement<Object>(_GetPopularMovieRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://movies-soa.com/app", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticatedUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://movies-soa.com/app", name = "authenticatedUser")
    public JAXBElement<AuthenticatedUser> createAuthenticatedUser(AuthenticatedUser value) {
        return new JAXBElement<AuthenticatedUser>(_AuthenticatedUser_QNAME, AuthenticatedUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Movie }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://movies-soa.com/app", name = "movie")
    public JAXBElement<Movie> createMovie(Movie value) {
        return new JAXBElement<Movie>(_Movie_QNAME, Movie.class, null, value);
    }

}
