import { Wallet } from './wallet.model';

export class User {
  public id: number;
  public name: string;
  public lastName: string;
  public email: string;
  public wallets: Wallet [];

  constructor(
    id: number,
    name: string,
    lastName: string,
    email: string,
    wallets: Wallet[]
  ) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.wallets = wallets;
  }

}
