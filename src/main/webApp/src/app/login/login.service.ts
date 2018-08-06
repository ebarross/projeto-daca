import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

const path = 'http://localhost:8080/login';
const header = new Headers({'Content-Type': 'application/json'});
const options = new RequestOptions({ headers: header});

@Injectable()
export class LoginService {

    constructor(private http: Http) {}

    doLogin(body) {
        return this.http.post(path, body, options);
    }

}
