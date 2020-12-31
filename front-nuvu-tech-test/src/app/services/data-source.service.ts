import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {throwError} from 'rxjs';
import {LoginRequest} from '../model/login-request';
import {catchError} from 'rxjs/operators';
import {SessionService} from './session.service';
import {Client} from '../model/client';
import {CreateClientRequest} from '../model/create-client-request';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: 'root'
})
export class DataSourceService {

  private serverURL: string = environment.restApiServer;
  private loginURL: string = this.serverURL + '/auth/login';
  private clientListURL: string = this.serverURL + '/clients';
  private idTypesURL: string = this.serverURL + '/clients/id_types';
  private cardsURL: string = this.serverURL + '/cards';

  constructor(
    private httpClient: HttpClient,
    private sessionService: SessionService
  ) { }

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
    this.cleanAuthorization();
    return this.httpClient
      .post(this.loginURL, requestLogin, httpOptions)
      .pipe(catchError(this.handleError));
  }

  public getClientsRequest(): any {
    this.verifyAuthorization();
    return this.httpClient
      .get(this.clientListURL, httpOptions)
      .pipe(catchError(this.handleError));
  }

  public getClientByIdRequest(userId: number): any {
    this.verifyAuthorization();
    return this.httpClient
      .get(this.clientListURL + '/' + userId , httpOptions)
      .pipe(catchError(this.handleError));
  }

  public getIdTypes(): any {
    this.verifyAuthorization();
    return this.httpClient
      .get(this.idTypesURL, httpOptions)
      .pipe(catchError(this.handleError));
  }

  private verifyAuthorization(): void  {
    if ( !httpOptions.headers.get('Authorization') ){
      httpOptions.headers = httpOptions.headers.append('Authorization', this.sessionService.getItem('token'));
    }
  }

  private cleanAuthorization(): void  {
    if ( httpOptions.headers.get('Authorization') ){
      httpOptions.headers = httpOptions.headers.delete('Authorization');
    }
  }

  public getCreateClientRequest(newClient: CreateClientRequest): any {
    this.verifyAuthorization();
    return this.httpClient
      .post(this.clientListURL, newClient, httpOptions)
      .pipe(catchError(this.handleError));
  }

  public getClientCardsRequest(userId: number): any {
    this.verifyAuthorization();
    return this.httpClient
      .get(this.cardsURL + '?user_id=' + userId, httpOptions)
      .pipe(catchError(this.handleError));
  }

}
