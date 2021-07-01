import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductModel } from 'src/app/models/productModel';
import "@google/model-viewer";
import { ReviewService } from 'src/app/services/review.service';
import { ReviewModel } from 'src/app/models/reviewModel';
import { StarRatingComponent } from 'ng-starrating';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  reviews: ReviewModel[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProductModel,
    private reviewService: ReviewService
  ) { }

  ngOnInit(): void {
    this.reviewService.findAllProductReviews(this.data.id).subscribe( response => { this.reviews = response.body } );
  }

}
