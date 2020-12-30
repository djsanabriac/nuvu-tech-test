import { Component, OnInit } from '@angular/core';
import {SessionService} from '../../services/session.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private _sessionSevice: SessionService,
    private _router: Router
  ) { }

  ngOnInit(): void {
  }

  logout(){
    this._sessionSevice.logout();
    this._router.navigate(['']);
  }

}
