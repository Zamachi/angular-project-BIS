import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductModel } from '../models/productModel';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<ProductModel[]> {
    const url = "http://localhost:8080/auth/login";

    return this.http.post<ProductModel[]>(url, { observe: 'body' });
  }
}
