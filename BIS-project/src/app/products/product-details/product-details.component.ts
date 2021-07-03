import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductModel } from 'src/app/models/productModel';
import "@google/model-viewer";
import { ReviewService } from 'src/app/services/review.service';
import { ReviewModel } from 'src/app/models/reviewModel';
import { StarRatingComponent } from 'ng-starrating';
import { add } from 'cart-localstorage';
import { UserService } from 'src/app/services/user.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  reviews: ReviewModel[];
  isAlreadyInTheCart: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProductModel,
    private reviewService: ReviewService,
    private userService: UserService,
    private router: Router,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.reviewService.findAllProductReviews(this.data.id).subscribe( response => { this.reviews = response.body; } );
    this.isAlreadyInTheCart = this.isInTheCart();
  }

  addToCart(){
    this.cartService.addItemToCart(this.data);
    this.isAlreadyInTheCart = this.isInTheCart();
  }

  removeFromCart() {
    this.cartService.removeFromCart(this.data);
    this.isAlreadyInTheCart = this.isInTheCart();
  }

  isInTheCart(): boolean {
    return this.cartService.alreadyInCart(this.data);
  }
}
