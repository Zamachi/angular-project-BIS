import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { UserModel } from '../models/userModel';
import { ProductService } from '../services/product.service';
import { UserService } from '../services/user.service';
import {
  MatAutocomplete,
  MatAutocompleteSelectedEvent,
} from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { map, startWith } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { OrderModel } from '../models/orderModel';
import { MatTableDataSource } from '@angular/material/table';
import { OrderService } from '../services/order.service';
import { ReviewModel } from '../models/reviewModel';
import { ReviewService } from '../services/review.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css'],
})
export class UserprofileComponent implements OnInit, AfterViewInit {
  user: UserModel;
  categoryControl = new FormControl();
  separatorKeysCodes: number[] = [ENTER, COMMA];
  allCategories: string[] = [];
  filteredCategories: Observable<string[]>;
  category: any;

  @ViewChild('categoryInput') categoryInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  //NOTE: orders tab
  orders = new MatTableDataSource<OrderModel>();
  displayedColumnsOrders = [
    'No.',
    'id',
    'dateCreated',
    'totalPrice',
    'paymentOption',
    'status',
    'complete',
    'cancel',
  ];

  numberOfCompleted: number = 0;
  numberOfOngoing: number = 0;
  numberOfCancelled: number = 0;

  
  //NOTE: reviews tab

  myReviews = new MatTableDataSource<ReviewModel>();
  displayedColumnsReviews = [
    'Numero', 'product.name', 'comment', 'score', 'dateCreated'
  ];

  // for more than one mat paginator, we have to use ViewChildren and List of Paginators
  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();
  @ViewChildren(MatSort) sort = new QueryList<MatSort>();

  constructor(
    private userService: UserService,
    private productService: ProductService,
    private orderService: OrderService,
    private reviewService: ReviewService,
    private _snackBar: MatSnackBar
  ) {
    this.filteredCategories = this.categoryControl.valueChanges.pipe(
      startWith(null), //<- ukoliko korisnik klikne na input polje i ne unese nista, prikazace svo voce
      map((category: string | null) =>
        category ? this._filter(category) : this.allCategories.slice()
      )
    );
    //Ako je "fruit" null filter pokazuje SVO voce,
    //u suprotnom voca koja se poklapaju sa imenom u inptu polju
  }

  ngOnInit(): void {
    this.userService
      .getUserByUsernameFromTheServer(localStorage.getItem('username'))
      .subscribe((response: UserModel) => {
        this.user = response;
        if (!this.user.favourites) {
          this.user.favourites = [];
        }
      });

    this.productService
      .findAllCategories()
      .subscribe((response) => (this.allCategories = response));

    this.orderService
      .fetchAllUserOrders()
      .subscribe((response) => (this.orders.data = response));

    this.reviewService
      .findAllUserReviews({ username: localStorage.getItem('username') })
      .subscribe((response) => (this.myReviews.data = response.body));
  }

  ngAfterViewInit(){
    this.orders.paginator = this.paginator.toArray()[0];
    this.orders.sort = this.sort.toArray()[0];
    
    this.myReviews.paginator = this.paginator.toArray()[1];
    this.myReviews.sort = this.sort.toArray()[1];
    
    setTimeout(() => {
      this.orders.data.forEach((order: OrderModel) => {
        
        if (order.status == "ongoing") {
          this.numberOfOngoing += 1;
        } else if (order.status == "complete") {
          this.numberOfCompleted += 1;
        } else if (order.status == "cancelled") {
          this.numberOfCancelled += 1;
        }

      });
    }, 500);
  }

  onSubmit(form: NgForm) {
    console.log(form);
    console.log(this.user.favourites);
    this.userService
      .updateUser({
        id: this.user.id,
        password: form.value.passwordConfirm,
        username: localStorage.getItem('username'),
        email: form.value.email,
        favourites: this.user.favourites,
        firstName: form.value.firstName,
        lastName: form.value.lastName,
        phone: form.value.phone,
        address: {
          city: form.value.city,
          country: form.value.country,
          number: form.value.number,
          street: form.value.street,
          zipCode: form.value.zipCode,
        },
      })
      .subscribe((response) => {
        if (response != null) {
          this._snackBar.open('Successfully updated', '', { duration: 2500 });
          window.location.reload();
        } else {
          this._snackBar.open('ERROR WHILST UPLOADING', 'Too bad', {
            duration: 2500,
          });
        }
      });
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

    return this.allCategories.filter((category) => {
      return category.toLowerCase().indexOf(filterValue) === 0;
    });
  }

  complete(element_id) {
    this.orderService
      .updateOrder({
        id: element_id,
        status: 'complete',
      })
      .subscribe((response) => {
        if (response != null) {
          this.orders.data.find((order) => order.id == element_id).status =
            response.status;
          this._snackBar.open('Successfully completed the order', '', {
            duration: 2500,
          });
        }
      });
  }

  cancel(element_id) {
    this.orderService
      .updateOrder({
        id: element_id,
        status: 'cancelled',
      })
      .subscribe((response) => {
        if (response != null) {
          this.orders.data.find((order) => order.id == element_id).status =
            response.status;
          this._snackBar.open('Successfully cancelled the order', '', {
            duration: 2500,
          });
        }
      });
  }

  doFilterOrders(filterValue: string) {
    this.orders.filter = filterValue.trim().toLocaleLowerCase();
  }

  doFilterReviews(filterValue: string) {
    this.myReviews.filter = filterValue.trim().toLocaleLowerCase();
  }

}
