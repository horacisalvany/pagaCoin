import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Transfer } from '../model/transfer.model';
import { Wallet } from '../model/wallet.model';
import { TransferService } from '../service/transfer-service';
import { WalletService } from '../service/wallet-service';

@Component({
  selector: 'app-wallet-list',
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.css']
})
export class WalletListComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private walletService: WalletService,
    private transferService: TransferService
    ) {}

  wallets: Wallet[] = [];
  walletsDestination: Wallet[] = [];
  transfer: Transfer;
  selectedSourceOption: number;
  selectedDestinationOption: number;
  amount = 0;
  transferCreated = false;
  disableAllInputs = false;
  @ViewChild('f') form: NgForm;
  @ViewChild('alertWarning', { read: ElementRef }) alertWarning: ElementRef;
  @ViewChild('alertSuccess', { read: ElementRef }) alertSuccess: ElementRef;
  @ViewChild('alertInfo', { read: ElementRef }) alertInfo: ElementRef;

  ngOnInit() {
    this.route.data
    .subscribe((data) => {
      if (data && data.walletsInfo) {
        console.log(data);
        this.wallets = data.walletsInfo;
        this.walletsDestination = this.wallets.slice();
        this.walletService.saveWallets(this.wallets);
        this.wallets.sort ( (a, b) => (a.id) > (b.id) ? 1 : -1 );
        this.walletsDestination.sort ( (a, b) => (a.id) > (b.id) ? 1 : -1 );
      }
    });
  }

  onSubmit() {
    if (this.transferCreated === false) {
      this.disableAllInputs = true;
      this.alertWarning.nativeElement.hidden = true;
      const walletSource = this.wallets.find (i => i.id == this.selectedSourceOption);
      const walletDestination = this.wallets.find (i => i.id == this.selectedDestinationOption);
      console.log(walletSource);
      console.log(walletDestination);
      this.transfer = {
        source: walletSource,
        destination: walletDestination,
        amount: this.amount
      } as Transfer;
      console.log(this.transfer);
      this.transferService.createTransfer(this.transfer).then ( (data: any) => {
        if (data && data.id != null) {
          this.transfer = data;
          console.log(this.transfer);
          this.transferCreated = true;
          this.alertInfo.nativeElement.hidden = false;
        }
      });
    } else {
      this.transfer.amount = this.amount;
      this.transferService.commitTransfer(this.transfer).then( () =>  this.transferCreated = false).
      then(()  => {
        this.alertWarning.nativeElement.hidden = true;
        this.alertInfo.nativeElement.hidden = true;
        this.alertSuccess.nativeElement.hidden = false;
        this.disableAllInputs = false;
        this.form.reset();
      })
      .catch(error => {
        this.alertWarning.nativeElement.hidden = false;
        this.alertSuccess.nativeElement.hidden = true;
        this.alertInfo.nativeElement.hidden = true;
        this.alertWarning.nativeElement.innerText = 'Warning! ' + error.error;
        this.transferCreated = false;
      });
    }
  }

  onChangeSource() {
    this.walletsDestination = this.wallets.slice();
    console.log(this.form);
    console.log(this.form.controls.amount.value);
    this.amount = this.form.controls.amount.value;
    this.walletsDestination.splice(this.findIndexWallet(this.wallets, this.selectedSourceOption), 1);
  }

  onClickCancelTransfer() {
    this.form.reset();
    this.alertInfo.nativeElement.hidden = true;
    this.transferCreated = false;
    this.disableAllInputs = false;
  }

  private findIndexWallet(wallets: Wallet[], index: number): number {
    let aux = 0;
    let i = 0;
    wallets.forEach ( (wallet: Wallet) => {
      if (wallet.id == index ) {
        aux = i;
      }
      ++i;
    } );
    return aux;
  }
}
