import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderModel } from '../models/orderModel';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(orderModel: OrderModel): Observable<OrderModel> {
    const url = "http://localhost:8080/orders/createorder";

    return this.http.post<OrderModel>(url, orderModel, { observe: 'body' });
  }
}
