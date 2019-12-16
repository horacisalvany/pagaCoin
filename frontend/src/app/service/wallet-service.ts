import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Wallet } from '../model/wallet.model';
import { RestClient } from './rest-client';

@Injectable()
export class WalletService {
  private wallets: Wallet[];

  constructor(
    private restClient: RestClient
    ) {
      this.wallets = [];
    }

    resolve(route: ActivatedRouteSnapshot): Promise<any> | boolean {
      return this.restClient.call('http://localhost:8081/api/wallet', 'GET').toPromise();
    }

    saveWallets(wallets: Wallet[]) {
      console.log(wallets);
      this.wallets = wallets;
    }
}
