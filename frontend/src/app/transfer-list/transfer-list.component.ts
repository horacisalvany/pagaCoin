import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Transfer } from '../model/transfer.model';
import { RestClient } from '../service/rest-client';

@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.css']
})
export class TransferListComponent implements OnInit, OnDestroy {
  dtOptions: DataTables.Settings = {};

  constructor(private restClient: RestClient) {}

  transfers: Transfer[] = [];
  dtTrigger = new Subject();

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };
    this.restClient
      .call('http://localhost:8081/api/transfer', 'GET')
      .toPromise()
      .then((data: any) => {
        console.log(data);
        this.transfers = data;
        this.dtTrigger.next();
      });
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }
}
