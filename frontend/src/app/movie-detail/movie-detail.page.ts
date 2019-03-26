import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.page.html',
  styleUrls: ['./movie-detail.page.scss'],
})
export class MovieDetailPage implements OnInit {

  //retrieved from back-end
  movie: any = {};
  recommended: Object[];
  slideOpts = {
    slidesPerView: 3,
    spaceBetween: 10
  }

  constructor() {
    this.movie = {
      "name": "Avengers",
      "photo": "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_poster.jpg",
      "overview": "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
      "genres": [
        "Action",
        "Drama",
        "Superheroes"
      ],
      "rating": 3,
      "id": 5,
      "year": 2019,
      "added": true
    }

    this.recommended = [
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
      },
      {
        "name": "Captain Marvel",
        "photo": "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_poster.jpg",
        "overview": "overview",
        "rating": 5,
        "id": 12
      },
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

  isAdded() {
    return this.movie.added ? "-" : "+";
  }

  changeCollectionStatus(){
    this.movie.added = !this.movie.added;
  }

}
