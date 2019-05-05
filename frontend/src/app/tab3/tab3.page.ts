import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  private movies: Object[] = [];
  private loaded : Boolean = true;

  constructor(private router: Router, private httpManager: HttpManagerService) {
  }

  ionViewWillEnter(){
    this.loadCollection();
  }

  loadCollection(){
    (<any>this.httpManager.client).getPersonalMovies({}).subscribe(
      (res: ISoapMethodResponse) => {
        this.movies = res.result.meta_movie.map(movie => {
          return {
            id: movie.tnmb,
            name: movie.name
          }
        });
      }
    )
  }

  openMovie(element){
    this.router.navigateByUrl("/movie/" + element.id);
  }
}
