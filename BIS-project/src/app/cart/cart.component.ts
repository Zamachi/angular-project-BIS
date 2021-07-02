import { AfterViewInit, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OrderModel } from '../models/orderModel';
import { ProductModel } from '../models/productModel';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, AfterViewInit {

  items = [];
  paymentMethods = ["VISA", "Master Card", "PayPal", "Crypto"];

  paymentValue = '';

  constructor(private cartService: CartService, private orderService: OrderService, private matSnackBar: MatSnackBar, private authService: AuthService) { }

  ngAfterViewInit(): void {
    // this.items.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.items = this.cartService.getAllFromCart();
  }

  buy() {
    // let orderModel: OrderModel = {
    //   items: this.items,
    //   user: this.authService;
    //   totalPrice: number;
    //   dateCreated: Date;
    //   status: string;
    //   paymentOption: string;
    // }

    this.orderService.createOrder().subscribe((result) => {
      if (result != null) {
        this.matSnackBar.open("Order created!", "", { duration: 2500 });
      } else {
        this.matSnackBar.open("Could not create order...", "", { duration: 2500 });
      }
    })
  }

  getCurrentProductQuantityFromCart(product: ProductModel) {
    return this.cartService.getProductFromCart(product).quantity;
  }

  increaseProductQuantity(value: any, product: ProductModel) {
    value = "" + (+value+1);

    if (value <= product.leftInStock) {
      this.cartService.updateQuantity(product, value);
    }

  }

  decreaseProductQuantity(value: any, product: ProductModel) {
    value = "" + (+value-1);

    if (value > 0) {
      this.cartService.updateQuantity(product, value);
    }

  }

  removeFromCart(product: ProductModel) {
    this.cartService.removeFromCart(product);
    this.items = this.cartService.getAllFromCart();
  }

  calculateTotal(){
    return this.cartService.totalCartValue();
  }

}
