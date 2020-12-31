import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DataSourceService} from '../../services/data-source.service';
import {GeneralResponse} from '../../model/general-response';
import {Client} from '../../model/client';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {IdType} from '../../model/id-type';
import {CreateClientRequest} from '../../model/create-client-request';
import {Router} from '@angular/router';
import {SessionService} from '../../services/session.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clientList: Client[] | undefined;
  idTypes: IdType[] | undefined;
  showError: boolean = false;
  errorMessage: string | undefined;
  userForm: FormGroup;
  showModalError: boolean = false;
  errorModalMessage: string;
  showSuccess: boolean = false;
  sucessModalMessage: string ;

  constructor(
    private _dataSource: DataSourceService,
    private fb: FormBuilder,
    private _router: Router,
    private sessionService: SessionService
  ) {

    this.userForm = this.fb.group({
      name: new FormControl("", [Validators.required]),
      last_name: new FormControl("", [Validators.required]),
      id_number: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      phone_number: new FormControl("", [Validators.required]),
      id_type: new FormControl(null, [Validators.required]),
    });
    this.getClients();

    this._dataSource
      .getIdTypes()
      .subscribe(
        (resp: GeneralResponse) => {
          if ( !resp.success ){
            this.showError = true;
            return;
          }

          this.showError = false;
          this.idTypes = resp.data;

        },
        (err: any) => {
          this.showError = true;
          this.errorMessage = err.error.message;
        }
      );

  }

  private getClients(): void {
    this._dataSource
      .getClientsRequest()
      .subscribe(
        (resp: GeneralResponse) => {
          if (!resp.success) {
            this.showError = true;
            return;
          }

          this.showError = false;
          this.clientList = resp.data;

        },
        (err: any) => {
          this.showError = false;
          this.errorMessage = err.message;
          console.log(err);
          if ( err.code === 403 ){
            this.sessionService.logout();
            this._router.navigate(['']);
          }
        }
      );
  }

  ngOnInit(): void {
  }

  cleanForm(){
    this.userForm.reset();
  }

  createUser(){
    this.showError = false;
    this.showModalError = false;

    const client: Client = this.userForm.value;

    const clientRequest: CreateClientRequest = {
      name: client.name,
      last_name: client.last_name,
      id_type: client.id_type.id,
      id_number: client.id_number,
      email: client.email,
      phone_number: client.phone_number
    };

    this._dataSource
      .getCreateClientRequest(clientRequest)
      .subscribe(
        (resp: GeneralResponse) => {
          if ( !resp.success ) {
            this.showError = true;
            return;
          }
          this.getClients();
          this.showSuccess = true;
          this.sucessModalMessage = resp.message;
          this.cleanForm();

        },
        (err: any) => {
          this.showModalError = true;
          this.errorModalMessage = err.error.message;
          console.log(err);
        }
      );

  }

  goToClient(id: number|undefined) {
    this._router.navigate(['home','client', id]);
  }
}
