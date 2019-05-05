import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.page.html',
  styleUrls: ['./movie-detail.page.scss'],
})
export class MovieDetailPage implements OnInit {

  movieLoaded: boolean = false;
  recommendationsLoaded: boolean = false;
  loaded: boolean = false;
  added: boolean;
  movie: Object = {};
  currentId: string;
  recommended: Object[];
  slideOpts = {
    slidesPerView: 2.6,
    spaceBetween: 15
  }

  constructor(private router: Router, private httpManager: HttpManagerService) {
    this.currentId = this.router.url.split("/")[2];
  }

  ngOnInit() {
    this.getMovieDetail();
    this.getRecommended();
  }

  modifyLoaded() {
    if (this.movieLoaded && this.recommendationsLoaded) {
      this.loaded = true;
    }
  }

  getMovieDetail() {
    const body = {
      "sch:movie_id": this.currentId
    };

    (<any>this.httpManager.client).getMovieById(body).subscribe(
      (res: ISoapMethodResponse) => {
        this.movie = {
          title: res.result.movie.title,
          rating: res.result.movie.rating,
          year: res.result.movie.year,
          tagline: res.result.movie.tagline,
          photo: res.result.movie.photo,
          id: res.result.movie.movie_id,
          genres: res.result.movie.genres.genre
        }

        this.added = res.result.added;
        this.movieLoaded = true;
        this.modifyLoaded();
      }
    )
  }

  getRecommended() {
    const body = {
      "sch:movie_id": this.currentId
    };

    (<any>this.httpManager.client).getRecommendations(body).subscribe(
      (res: ISoapMethodResponse) => {
        this.recommended = res.result.movies.movie.map(movie => {
          return {
            id: movie.movie_id,
            photo: movie.photo
          }
        })

        this.recommendationsLoaded = true;
        this.modifyLoaded();
      }
    )
  }

  isAdded() {
    return this.added ? "-" : "+";
  }

  changeCollectionStatus() {
    const body = {
      "sch:movie_id": this.currentId
    };

    (<any>this.httpManager.client).saveMovieToCollection(body).subscribe(
      (res: ISoapMethodResponse) => {
        this.added = res.result.added;
      }
    )
  }

  navigateBack() {
    window.history.back();
    this.movie = {};
  }

  openRecommendation(element) {
    this.router.navigateByUrl("/movie/" + element.id);
  }

}
