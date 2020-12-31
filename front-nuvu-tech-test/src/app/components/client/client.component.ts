import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Client} from '../../model/client';
import {Card} from '../../model/card';
import {DataSourceService} from '../../services/data-source.service';
import {GeneralResponse} from '../../model/general-response';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  client:Client;
  cards:Card[];
  showSuccess: boolean;
  showModalError: boolean;
  errorModalMessage: string;
  sucessModalMessage: string;
  cardForm: FormGroup;

  constructor(
    private _router: Router,
    private route: ActivatedRoute,
    private _dataSource: DataSourceService,
    private fb: FormBuilder,
  ) {

    this._dataSource
      .getClientByIdRequest(this.route.snapshot.params['id'])
      .subscribe(
        (resp: GeneralResponse) => {
          this.client = resp.data;
        },
        (err: any) => {

        }
      );

    this._dataSource
      .getClientCardsRequest(this.route.snapshot.params['id'])
      .subscribe(
        (resp: GeneralResponse) => {
          this.cards = resp.data;
        },
        (err: any ) => {
          console.error(err);
        }
      );
    console.log(this.route.snapshot.params['id']);

  }

  ngOnInit(): void {
  }

}
