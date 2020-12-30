import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators,
  FormBuilder
} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from '../../model/login-request';
import {DataSourceService} from '../../services/data-source.service';
import {GeneralResponse} from '../../model/general-response';
import {MessagesService} from '../../services/messages.service';
import {SessionService} from '../../services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public errorMessage: string;
  public showError: boolean = false;

  constructor(
    private _router: Router,
    private fb: FormBuilder,
    private _dataSource: DataSourceService,
    private _messagesService: MessagesService,
    private _sessionService: SessionService
  ) {

    this.loginForm = this.fb.group({
      username: new FormControl("", [Validators.required]),
      password: new FormControl("", Validators.required)
    });
  }

  ngOnInit(): void {

    if( this._sessionService.isLoggedIn() ){
      this._router.navigate(['home', 'clients']);
    }

  }


  login(): void {

    this.showError = false;

    const loginRequest: LoginRequest= this.loginForm.value;

    this._dataSource
      .getAuthenticationRequest(loginRequest)
      .subscribe(
        (data: GeneralResponse) => {
          const response = data;

          if (response.success){
            this._router.navigate(['home', 'clients']);
            this._sessionService.setSessionInfo(response.data);
          }

        },
        (err: any) => {
          this.showError = true;
          this.errorMessage = this._messagesService.getMessage('login', err.error.message);
        }
      );


  }
}
