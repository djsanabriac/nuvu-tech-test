import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  private messages: any;

  constructor(
    private httpClient: HttpClient
  ) {
    this.getDataJson();
  }

  getDataJson(): void {
    this.httpClient.get('assets/messages.json').subscribe(data => {
      this.messages = data;
      console.log(this.messages);
    });
  }

  public getMessage( view: string, msg: string ): any {
    return this.messages[view][msg];
  }

}
