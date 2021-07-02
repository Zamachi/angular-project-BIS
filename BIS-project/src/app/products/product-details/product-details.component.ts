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

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  reviews: ReviewModel[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProductModel,
    private reviewService: ReviewService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.reviewService.findAllProductReviews(this.data.id).subscribe( response => { this.reviews = response.body } );
  }

  addToCart(){
    add(this.data);
  }
}
