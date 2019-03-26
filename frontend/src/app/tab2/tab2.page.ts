import { Component } from '@angular/core';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  private searchText: String = "";
  private movies = [];
  private loaded: Boolean = false;

  constructor() {
    this.movies = [
      {
        "name": "Avengers",
        "photo": "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_poster.jpg",
        "overview": "overview",
        "rating": 3,
        "id": 5
      },
      {
        "name": "Captain Marvel",
        "photo": "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_poster.jpg",
        "overview": "overview",
        "rating": 5,
        "id": 12
      }
    ]
  }

  dispatchSearch() {
    console.log(this.searchText);
  }
}
