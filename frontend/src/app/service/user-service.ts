import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { User } from '../model/user.model';
import { RestClient } from './rest-client';

@Injectable()
export class UserService {

  private users: User[];

  constructor(
    private restClient: RestClient
    ) {
      this.users = [];
    }

  resolve(route: ActivatedRouteSnapshot): Promise<any> | boolean {
    return this.restClient.call('http://localhost:8081/api/user', 'GET').toPromise();
  }

  getUser(index: number) {
    return this.users[index];
  }

  saveUsers(users: User[]) {
    this.users = users;
  }

}
