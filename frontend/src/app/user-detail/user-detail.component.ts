import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { User } from '../model/user.model';
import { UserService } from '../service/user-service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private userService: UserService
    ) { }

    id: number;
    userDetails: User;

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.userDetails = this.userService.getUser(this.id);
    });
  }

}
