import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';

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
     const body = {
       "sch:user": {
        "sch:username": "alexb",
        "sch:password": "alex12344"
       }
    };

     (<any>this.httpManager.client).login(body).subscribe((res: ISoapMethodResponse) => {
       console.log(res.result);
     })
  }

}
