import { Component, Input } from '@angular/core';
import { Wallet } from '../model/wallet.model';

@Component({
  selector: 'app-wallet-item',
  templateUrl: './wallet-item.component.html',
  styleUrls: ['./wallet-item.component.css']
})
export class WalletItemComponent {


  constructor() { }
  @Input() wallet: Wallet;
  @Input() index: number;
}
