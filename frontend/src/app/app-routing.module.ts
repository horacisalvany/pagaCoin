import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './core/home/home.component';
import { UserService } from './service/user-service';
import { WalletService } from './service/wallet-service';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserListComponent } from './user-list/user-list.component';
import { WalletListComponent } from './wallet-list/wallet-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'users', component: UserListComponent, resolve: {usersInfo: UserService},
  children: [
  {
    path: ':id',
    component: UserDetailComponent
  }]
  },
  {path: 'transfers', component: WalletListComponent, resolve: {walletsInfo: WalletService}}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
