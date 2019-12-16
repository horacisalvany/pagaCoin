import { User } from './user.model';

export class Wallet {
  public id: number;
  public balance: number;
  public holder: User;

  constructor(
    balance: number,
    holder: User
  ) {
    this.balance = balance;
    this.holder = holder;
  }
}
