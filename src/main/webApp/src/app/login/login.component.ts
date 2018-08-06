import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginSucesso: boolean = true;
    email: string;
    senha: string;

    constructor(private router: Router,
                private service: LoginService) { }

    login() {
        const user = JSON.stringify({
            email: this.email,
            senha: this.senha
        });
        // console.log(user);
        this.service.doLogin(user).subscribe(data => {
            if (data.status == 200) {
                this.router.navigate(['home']);
            } else {
                this.loginSucesso = false;
            }
            console.log(data.status == 200);
        });
    }

    ngOnInit() {
    }

}
