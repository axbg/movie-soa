import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

  private searchText: String = "";
  private movies: Object[] = [];
  private loaded: Boolean = true;

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

  ngOnInit() {
  }

  dispatchSearch() {
    console.log(this.searchText);
  }
}
