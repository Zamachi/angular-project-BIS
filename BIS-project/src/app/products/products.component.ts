import { LabelType, Options } from '@angular-slider/ngx-slider';
import { Component, OnInit } from '@angular/core';
import { Sort, SortDirection } from '@angular/material/sort';
import { ProductModel } from '../models/productModel';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit, AfterViewInit {

  constructor(private productService: ProductService) { }

  sortValue: string = '';
  sortValueDirection: SortDirection = '';

  searchValue: string = "";
  catValue: string = "any";
  subCatValue: string = "any";
  p: number = 1;
  cat = new Map();

  minValuePrice: number = 1;
  maxValuePrice: number = 1000;
  optionsPrice: Options = {
    floor: 0,
    ceil: 1000,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return "<b style='color: #2697e7;'>Min:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
        case LabelType.High:
          return "<b style='color: #2697e7;'>Max:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
        default:
          return "<span  style='color: #2697e7;'>$</span>" + value;
      }
    }
  };

  minValueStock: number = 0;
  maxValueStock: number = 1000;
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

  dataOriginal: ProductModel[];

  ngOnInit(): void {
    this.findAll();


  }

  // -----------

  findUniqueCategoriesAndTheirSubCategoriesAndMap() {
    let uniqueCategories = [...new Set(this.data.map(item => item.category))];

    uniqueCategories.forEach((cat) => {
        let key = cat;
        let values = Array<string>();
        this.data.forEach((obj) => {
            if (obj.category.match(key)) {
                values.push(obj.subCategory);
            }
        });
        this.cat.set(key, values);
    });
  }


  // ------------ Sort

  sortData(sort: Sort) {

    this.sortValue = sort.active;
    this.sortValueDirection = sort.direction;

    const data = this.data.slice();
    if (!sort.active || sort.direction === '') {
      this.data = data;
      return;
    }

    this.data = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a.name, b.name, isAsc);
        case 'score': return this.compare(a.score, b.score, isAsc);
        case 'price': return this.compare(a.price, b.price, isAsc);
        default: return 0;
      }
    });


  }

  compare(a: number | string | DoubleRange, b: number | string | DoubleRange, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }


  // ------------

  private findAll() {
    this.productService.findAll().subscribe((result) => {
      if (result != null) {
        this.data = result;
        this.dataOriginal = result;
      }
    }).add(() => {
      this.findUniqueCategoriesAndTheirSubCategoriesAndMap();
      this.setUpPriceSlider();
      this.setUpStockSlider();

    });
  }

  setUpPriceSlider() {
    this.maxValuePrice = Math.max.apply(Math, this.data.map(function(o) { return o.price; }));
    this.minValuePrice = Math.min.apply(Math, this.data.map(function(o) { return o.price; }));

    this.optionsPrice = {
      floor: this.minValuePrice,
      ceil: this.maxValuePrice,
      translate: (value: number, label: LabelType): string => {
        switch (label) {
          case LabelType.Low:
            return "<b style='color: #2697e7;'>Min:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
          case LabelType.High:
            return "<b style='color: #2697e7;'>Max:</b> <span  style='color: #2697e7;'>$</span>" + "<span style='color: #2697e7;'>" + value + "</span>";
          default:
            return "<span  style='color: #2697e7;'>$</span>" + value;
        }
      }
    };
  }

  setUpStockSlider() {
    this.maxValueStock = Math.max.apply(Math, this.data.map(function(o) { return o.leftInStock; }));

    this.optionsStock = {
      floor: 0,
      ceil: this.maxValueStock
    }
  }

  singleProductDetails(id: string) {
    console.log(id);

  }

  search() {
    this.p = 1;

    let search = this.searchValue.trim().toLowerCase();
    let arr = this.dataOriginal;

    if (search != "") {
      arr = this.dataOriginal.filter(product => {
        return product.name.toLowerCase().includes(search)
      });
    }

    arr = arr.filter(product => {
      return product.price <= this.maxValuePrice && product.price >= this.minValuePrice;
    });

    arr = arr.filter(product => {
      return product.score <= this.maxValueScore && product.score >= this.minValueScore;
    });

    arr = arr.filter(product => {
      return product.leftInStock <= this.maxValueStock && product.leftInStock >= this.minValueStock;
    });

    if (this.catValue != "any") {
      arr = arr.filter(product => {
        return product.category == this.catValue;
      });
    } else {
      this.subCatValue = "any";
    }

    if (this.subCatValue != "any") {
      arr = arr.filter(product => {
        return product.subCategory == this.subCatValue;
      });
    }

    this.data = arr;

    this.sortData({active: this.sortValue, direction: this.sortValueDirection});
  }

}
