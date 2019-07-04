import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {
  private user: Object = { "username": "" };

  constructor(private router: Router) {
    this.user["username"] = window.localStorage.getItem("username");
  }

  logout() {
    window.localStorage.removeItem("token");
    window.localStorage.removeItem("username");
    this.router.navigateByUrl("");
  }
}
