import { Injectable } from '@angular/core';
import { list, update, total, get, add, exists, remove } from 'cart-localstorage';
import { ProductModel } from '../models/productModel';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor() { }

  addItemToCart(product: ProductModel) {
    add(product, 1);
  }

  removeFromCart(product: ProductModel) {
    remove(product.id);
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
}
