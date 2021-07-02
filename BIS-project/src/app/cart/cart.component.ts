import { AfterViewInit, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { OrderItemModel } from '../models/orderItemModel';
import { OrderModel } from '../models/orderModel';
import { ProductModel } from '../models/productModel';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, AfterViewInit {

  items = [];
  paymentMethods = ["VISA", "Master Card", "PayPal", "Crypto"];
  paymentValue = '';

  constructor(private cartService: CartService, private orderService: OrderService, private matSnackBar: MatSnackBar, private userService: UserService) { }

  ngAfterViewInit(): void {
    // this.items.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.items = this.cartService.getAllFromCart();
  }

  buy() {
    this.items = this.cartService.getAllFromCart();

    if (this.items.length < 1) {
      this.matSnackBar.open("No items in your cart!", "", { duration: 2500 });
      return;
    }
    if (this.paymentValue == '' || this.paymentValue.length < 1) {
      this.matSnackBar.open("Please select a payment method", "", { duration: 2500 });
      return;
    }

    let orderItemModel: OrderItemModel[] = [];

    this.items.forEach((product) => {
      let itemModel: OrderItemModel = {
      "product" : {
        "id": product.id,
        "slug": product.slug,
        "name": product.name,
        "description": product.description,
        "leftInStock": product.leftInStock,
        "price": product.price,
        "manufacturer": product.manufacturer,
        "score": product.score,
        "imagePath": product.imagePath,
        "modelPath": product.modelPath,
        "address": product.address,
        "category": product.category,
        "subCategory": product.subCategory
      },
      "quantity": product.quantity
      };
      orderItemModel.push(itemModel);
    });

    let orderModel: OrderModel = {
      items: orderItemModel,
      user: this.userService.getCurrentUser(),
      totalPrice: this.cartService.totalCartValue(),
      paymentOption: this.paymentValue
    }

    console.log(orderModel);


    if (orderModel != null) {
      this.orderService.createOrder(orderModel).subscribe((result) => {
        if (result != null) {
          this.cartService.clearCart();
          this.items = this.cartService.getAllFromCart();
          this.matSnackBar.open("Order created!", "", { duration: 2500 });
        } else {
          this.matSnackBar.open("Could not create order...", "", { duration: 2500 });
        }
      });
    }
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
