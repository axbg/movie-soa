import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpManagerService } from '../http-manager.service';
import { ISoapMethodResponse } from 'ngx-soap';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  private form: FormGroup;
  private displayLogin: Boolean = true;

  constructor(private router: Router, private formBuilder: FormBuilder,
    private httpManager: HttpManagerService, private toastController: ToastController) {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  async ngOnInit() {
    if (window.localStorage.getItem("token")) {
      this.router.navigateByUrl("/app/main");
    } else {
      this.displayLogin = true;
    }
  }

  public dispatchLogin() {
    const body = {
      "sch:user": {
        "sch:username": this.form.value.username, //alexb
        "sch:password": this.form.value.password //"alex12344"
      }
    };

    (<any>this.httpManager.client).login(body).subscribe(
      async (res: ISoapMethodResponse) => {
        await window.localStorage.setItem("token", res.result.authenticatedUser.token);
        await window.localStorage.setItem("username", res.result.authenticatedUser.username);
        this.router.navigateByUrl("/app/main");
      },
      async () => {
        const loader = await this.toastController.create({
          message: 'Credentials are not correct.',
          duration: 2000,
          position: "top"
        });

        loader.present();
      })
  }

}
