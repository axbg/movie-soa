import { Component, OnInit } from '@angular/core';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

  private searchText: String = "";
  private movies: Object[] = [];
  private loaded: Boolean = false;
  private loadInterval;

  constructor(private httpManager: HttpManagerService) {
  }

  ngOnInit() {
    this.loadInterval = setInterval(() => {
      if(this.checkLoaded){
        clearInterval(this.loadInterval);
        this.httpManager.addAuthHeader();
        this.getPopularMovies();
      }
    }, 1000);
  }

  checkLoaded() {
    return this.httpManager.client ? true : false;
  }

  shortenMoviesTagline() {
    this.movies = this.movies.map(movie => {
      movie["tagline"] = movie["tagline"].length > 100 ? movie["tagline"].slice(0,100) + "..." : movie["tagline"];
      return movie;
    });
  }

  getPopularMovies() {
    (<any>this.httpManager.client).getPopularMovie({}).subscribe(
      (res: ISoapMethodResponse) => {
        this.movies = res.result.movies.movie;
        this.shortenMoviesTagline();
        this.loaded = true;
      }
    )
  }

  dispatchSearch() {
    console.log(this.searchText);
  }
}
