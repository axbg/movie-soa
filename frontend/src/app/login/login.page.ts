import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpManagerService } from '../http-manager.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  private form: FormGroup;
  private displayLogin: Boolean = true;

  constructor(private router: Router, private formBuilder: FormBuilder,
    private http: HttpClient, private httpManager: HttpManagerService) {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  ngOnInit() {
    /*
    if (window.localStorage.getItem("token")) {
      this.router.navigateByUrl("/app/main");
    } else {
      this.displayLogin = true;
    }
    */
   
  }

  public dispatchLogin() {
    this.http.get("https://jsonplaceholder.typicode.com/todos/1")
      .subscribe(response => {
        window.localStorage.setItem("token", "asdasdasd");
        window.localStorage.setItem("username", this.form.value.username);
        this.router.navigateByUrl("/app/main");
      })
  }

}
