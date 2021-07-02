import { Injectable } from '@angular/core';
import { list, update, total, get, add, exists, remove, destroy } from 'cart-localstorage';
import { BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';
import { ProductModel } from '../models/productModel';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartSize = new BehaviorSubject<number>(0);

  constructor() { }

  clearCart(){
    destroy();
    this.setCartSize();
  }

  addItemToCart(product: ProductModel) {
    add(product, 1);
    this.setCartSize();
  }

  removeFromCart(product: ProductModel) {
    remove(product.id);
    this.setCartSize();
  }

  getAllFromCart() {
    return list();
  }

  getProductFromCart(product: ProductModel) {
    return get(product.id);
  }

  alreadyInCart(product: ProductModel) {
    return exists(product.id);
  }

  updateQuantity(product: ProductModel, newQuantity: string) {
    update(product.id, "quantity", newQuantity);
  }

  totalCartValue() {
    return total();
  }

  setCartSize(){
    return this.cartSize.next( this.getCartSize() );
  }

  getCartSize():number{
    return this.getAllFromCart().length;
  }

  geter(){
    this.setCartSize();
    return this.cartSize;
  }

}
