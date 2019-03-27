import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  private movies: Object[] = [];
  private loaded : Boolean = true;

  constructor(private router: Router) {
    this.movies = [
      {
        "name": "Avengers",
        "id": 5
      },
      {
        "name": "Captain Marvel",
        "id": 12
      }
    ]
  }

  openMovie(element){
    this.router.navigateByUrl("/movie/" + element.id);
  }
}
