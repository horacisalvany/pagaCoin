import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { DataTablesModule } from 'angular-datatables';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './core/header/header.component';
import { HomeComponent } from './core/home/home.component';
import { RestClient } from './service/rest-client';
import { TransferService } from './service/transfer-service';
import { UserService } from './service/user-service';
import { WalletService } from './service/wallet-service';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserItemComponent } from './user-item/user-item.component';
import { UserListComponent } from './user-list/user-list.component';
import { WalletItemComponent } from './wallet-item/wallet-item.component';
import { WalletListComponent } from './wallet-list/wallet-list.component';
import { TransferListComponent } from './transfer-list/transfer-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserListComponent,
    HomeComponent,
    UserItemComponent,
    UserDetailComponent,
    WalletListComponent,
    WalletItemComponent,
    TransferListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DataTablesModule
  ],
  providers: [UserService, WalletService, TransferService, RestClient],
  bootstrap: [AppComponent]
})
export class AppModule {}
