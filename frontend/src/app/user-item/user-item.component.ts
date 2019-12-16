import { Component, Input } from '@angular/core';
import { User } from '../model/user.model';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent {

  constructor() { }
  @Input() user: User;
  @Input() index: number;

}
