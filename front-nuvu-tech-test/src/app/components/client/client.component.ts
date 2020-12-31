import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Client} from '../../model/client';
import {Card} from '../../model/card';
import {DataSourceService} from '../../services/data-source.service';
import {GeneralResponse} from '../../model/general-response';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CreateCardRequest} from '../../model/create-card-request';
import {SessionService} from '../../services/session.service';

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
    private sessionService: SessionService
  ) {

    this.cardForm = this.fb.group({
      card_holder_name: new FormControl("", [Validators.required]),
      card_number: new FormControl("", [Validators.required, Validators.min(0),Validators.required, Validators.max(9999999999999999)]),
      cvv: new FormControl("", [Validators.required]),
      exp_month: new FormControl("", [Validators.required]),
      exp_year  : new FormControl("", [Validators.required]),
    });

    this._dataSource
      .getClientByIdRequest(this.route.snapshot.params['id'])
      .subscribe(
        (resp: GeneralResponse) => {
          this.client = resp.data;
        },
        (err: any) => {
          if ( err.code === 403 ){
            this.sessionService.logout();
            this._router.navigate(['']);
          }
        }
      );

    this.getClientCards();
    console.log(this.route.snapshot.params['id']);

  }

  private getClientCards() : void {
    this._dataSource
      .getClientCardsRequest(this.route.snapshot.params['id'])
      .subscribe(
        (resp: GeneralResponse) => {
          this.cards = resp.data;
        },
        (err: any) => {
          console.error(err);
        }
      );
  }

  ngOnInit(): void {
  }

  cleanForm(){
    this.cardForm.reset();
  }

  createCard() {

    const newCard: CreateCardRequest = {
      cardHolderName: this.cardForm.value.card_holder_name,
      cardNumber: this.cardForm.value.card_number,
      cvv: this.cardForm.value.cvv,
      expirationDate: this.cardForm.value.exp_year + '-' + this.cardForm.value.exp_month + '-01',
      paymentNetworkId: 1,
      state: 'A',
      userId: this.client.id
    };

    this._dataSource
      .getCreateCardRequest(newCard)
      .subscribe(
        (resp: GeneralResponse) => {
          if( !resp.success ){

          }

          this.getClientCards();


        },
        (err:any) => {
          console.log(err);
        }
      );

  }
}
