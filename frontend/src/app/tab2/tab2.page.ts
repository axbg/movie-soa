import { Component, OnInit } from '@angular/core';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';
import { ToastController } from '@ionic/angular';

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

  constructor(private httpManager: HttpManagerService, private toastController: ToastController) {
  }

  ngOnInit() {
    this.loadInterval = setInterval(() => {
      if (this.checkLoaded) {
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
      movie["tagline"] = (movie["tagline"] && movie["tagline"].length > 100) ? movie["tagline"].slice(0, 100) + "..." : movie["tagline"];
      return movie;
    });
  }

  populateMovieList(movies) {
    if (movies) {
      this.movies = movies;
      this.shortenMoviesTagline();
      this.loaded = true;
    }
  }

  getPopularMovies() {
    (<any>this.httpManager.client).getPopularMovie({}).subscribe(
      (res: ISoapMethodResponse) => {
        this.populateMovieList(res.result.movies.movie);
        this.searchText = "";
      }
    )
  }

  dispatchSearch() {
    setTimeout(() => {
      const title = this.searchText.replace(/ /g, '%20');

      const body = {
        "sch:title": title
      };

      this.loaded = false;
      (<any>this.httpManager.client).getMoviesByName(body).subscribe(
        async (res: ISoapMethodResponse) => {
          if (res.result.movies) {
            await this.populateMovieList(res.result.movies.movie);
          } else {
            const loader = await this.toastController.create({
              message: 'Nothing found',
              duration: 2000,
              position: "top"
            });

            loader.present();
          }
          this.loaded = true;
        }
      )
    }, 200);
  }
}
