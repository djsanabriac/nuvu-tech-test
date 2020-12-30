import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {throwError} from 'rxjs';
import {LoginRequest} from '../model/login-request';
import {catchError} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: 'root'
})
export class DataSourceService {

  private serverURL: string = environment.restApiServer;
  private loginURL: string = this.serverURL + '/auth/login';

  constructor(
    private httpClient: HttpClient) { }

  handleError(error: HttpErrorResponse) {
    let errorMessage: any;
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = {
        error: `Error: ${error.error.message}`,
        code: 0,
        message: error.error.message
      };
    } else {
      // Server-side errors
      errorMessage = {
        error: error.error,
        code: error.status,
        message: error.message
      };
    }
    return throwError(errorMessage);
  }


  public getAuthenticationRequest(requestLogin: LoginRequest): any {
    return this.httpClient
      .post(this.loginURL, requestLogin, httpOptions)
      .pipe(catchError(this.handleError));
  }

}
