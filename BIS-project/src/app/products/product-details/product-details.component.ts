import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductModel } from 'src/app/models/productModel';
import "@google/model-viewer";


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ProductModel
  ) { }

  ngOnInit(): void {
  }

}
