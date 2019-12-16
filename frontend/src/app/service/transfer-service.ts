import { Injectable } from '@angular/core';
import { Transfer } from '../model/transfer.model';
import { RestClient } from './rest-client';

@Injectable()
export class TransferService {

  constructor(
    private restClient: RestClient) {}

  createTransfer(transfer: Transfer) {
    return this.restClient.call('http://localhost:8081/api/transfer', 'POST', transfer).toPromise();
  }

  commitTransfer(transfer: Transfer) {
    console.log(transfer);
    return this.restClient.call('http://localhost:8081/api/transfer/commit/' + transfer.id, 'PUT', transfer).toPromise();
  }
}
