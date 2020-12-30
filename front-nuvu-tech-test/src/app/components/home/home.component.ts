import { Component, OnInit } from '@angular/core';
import {SessionService} from '../../services/session.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public showNavBar: boolean = false;

  constructor(private _sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.showNavBar = this._sessionService.isLoggedIn();
  }

}
