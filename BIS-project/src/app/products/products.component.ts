import { Component, OnInit } from '@angular/core';
import { ProductModel } from '../models/productModel';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private productService: ProductService) { }

  data: ProductModel[];

  ngOnInit(): void {
    this.findAll();
  }

  private findAll() {
    this.productService.findAll().subscribe((result) => {
      if (result != null) {
        this.data = result;
      }
    }).add(() => console.log(this.data));
  }

  singleProductDetails(id: string) {
    console.log(id);
    
  }

  search(search: string) {
    search = search.trim().toLowerCase();
    if (search != "") {
      this.data = this.data.filter(product => { return product.name.toLowerCase().includes(search)} );
    }
    else {
      this.findAll();
    }
  }

}
