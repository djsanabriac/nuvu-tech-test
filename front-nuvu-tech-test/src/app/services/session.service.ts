import { Injectable } from '@angular/core';
import {Credentials} from '../model/credentials';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() {
  }

  setSessionInfo( si: any ){
    sessionStorage.setItem("user_id", si.user_id);
    sessionStorage.setItem("token", si.token);
  }

  isLoggedIn():boolean{
    if( sessionStorage.getItem("user_id") && sessionStorage.getItem("token") ){
      return true;
    }
    return false;
  }

  logout(){
    sessionStorage.clear();
  }

}
