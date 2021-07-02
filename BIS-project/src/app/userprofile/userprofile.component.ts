import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { UserModel } from '../models/userModel';
import { ProductService } from '../services/product.service';
import { UserService } from '../services/user.service';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {map, startWith} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { OrderModel } from '../models/orderModel';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  user: UserModel;
  categoryControl = new FormControl();
  separatorKeysCodes: number[] = [ENTER, COMMA];
  allCategories: string[];
  filteredCategories: Observable<string[]>;

  @ViewChild('categoryInput') categoryInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;


  orders: OrderModel[];
  displayedColumnsOrders = [];
  constructor(
    private userService: UserService,
    private productService: ProductService
  ) {
    this.filteredCategories = this.categoryControl.valueChanges.pipe(
      startWith(null),//<- ukoliko korisnik klikne na input polje i ne unese nista, prikazace svo voce
      map((category: string | null) => category ? this._filter(category) : this.allCategories.slice()));
      //Ako je "fruit" null filter pokazuje SVO voce, u suprotnom voca koja se poklapaju sa imenom u inptu polju
    }

  ngOnInit(): void {
    this
    .userService
    .getUserByUsernameFromTheServer(localStorage.getItem("username"))
    .subscribe( response => this.user = response);

    this
    .productService
    .findAllCategories()
    .subscribe(
      response => this.allCategories = response
    );

  }

  onSubmit(form: NgForm){

  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.user.favourites.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    this.categoryControl.setValue(null);
  }



  remove(category: string): void {
    const index = this.user.favourites.indexOf(category);

    if (index >= 0) {
      this.user.favourites.splice(index, 1);
    }
  }


  selected(event: MatAutocompleteSelectedEvent): void {
    this.user.favourites.push(event.option.viewValue);
    this.categoryInput.nativeElement.value = '';
    this.categoryControl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allCategories.filter(category => {
      return category.toLowerCase().indexOf(filterValue) === 0;
    });
  }
}
