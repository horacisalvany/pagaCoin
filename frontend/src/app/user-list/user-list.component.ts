import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../model/user.model';
import { UserService } from '../service/user-service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  constructor(private route: ActivatedRoute, private userService: UserService) { }
  users: User[] = [];

  ngOnInit() {
    this.route.data
    .subscribe((data) => {
      if (data && data.usersInfo) {
        console.log(data);
        this.users = data.usersInfo;
        this.userService.saveUsers(this.users);
        this.users.sort ( (a, b) => (a.lastName) > (b.lastName) ? 1 : (a.lastName === b.lastName) ? ((a.name > b.name) ? 1 : -1) : -1 );
      }
    });
  }

}
