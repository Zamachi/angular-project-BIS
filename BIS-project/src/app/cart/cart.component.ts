import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ProductModel } from '../models/productModel';
import { list, update, total, get } from 'cart-localstorage';
import { MatTableDataSource } from '@angular/material/table';
import { ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, AfterViewInit {

  items = new MatTableDataSource<any>();
  displayedColumns = ["name", "description", "price", "quantity","total"];


  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor() { }
  ngAfterViewInit(): void {
    this.items.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.items.data = list();
  }

  buy(){

  }

  azuriraj(kolicina, id_elementa){
    console.log(kolicina.value);
    update(id_elementa, "quantity", kolicina.value);
    // this.items.data = list(); //DANGER: NE RADI
    this.items.data.find(item => item.id == id_elementa).quantity = kolicina;
  }

  calculateTotal(){
    return total();
  }

}
