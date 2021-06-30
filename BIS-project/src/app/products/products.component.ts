import { LabelType, Options } from '@angular-slider/ngx-slider';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ProductModel } from '../models/productModel';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit, AfterViewInit {

  constructor(private productService: ProductService) { }

  catValue: any;
  subCatValue: any;

  cat = new Map();

  minValuePrice = 1;
  maxValuePrice = 10000;
  optionsPrice: Options = {
    floor: 0,
    ceil: 200,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return "<b style='color: #2697e7;'>Min price:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
        case LabelType.High:
          return "<b style='color: #2697e7;'>Max price:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
        default:
          return "<span  style='color: #2697e7;'>$</span>" + value;
      }
    }
  };

  minValueStock = 1;
  maxValueStock = 1000;
  optionsStock: Options = {
    floor: 0,
    ceil: 200
  }

  minValueScore = 0;
  maxValueScore = 5;
  optionsScore: Options = {
    floor: 0,
    ceil: 5
  }

  data: ProductModel[];

  ngOnInit(): void {
    this.findAll();

  }

  ngAfterViewInit(): void {
    // this.initializeCategories();
  }

  initializeCategories(){
    this.cat.clear();

    let unique = [...new Set(this.data.map(item => item.category))];
    let result = this.data.map(
    item => ({ [item.category]:item.subCategory }) );

    unique.forEach(
      uniqueCat => {
          let niz = [];
          niz = result
              .filter( testItem => Object.keys(testItem)[0] == uniqueCat )
              .map( item => Object.values(item)[0] );
          this.cat.set(uniqueCat, niz);
       }
  );


  }

  private findAll() {
    this.productService.findAll().subscribe((result) => {
      if (result != null) {
        this.data = result;
      }
    }).add(() =>  {this.initializeCategories();} );
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
