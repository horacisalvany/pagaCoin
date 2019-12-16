import { Wallet } from './wallet.model';

export class Transfer {
  public id: number;
  public source: Wallet;
  public destination: Wallet;
  public amount: number;
  public state: string;

  constructor(
    source: Wallet,
    destination: Wallet,
    amount: number
  ) {
    this.source = source;
    this.destination = destination;
    this.amount = amount;
  }
}
