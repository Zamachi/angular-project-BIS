import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReviewModel } from '../models/reviewModel';
import { UserModel } from '../models/userModel';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  url = "http://localhost:8080/reviews/";

  constructor(
    private httpClient: HttpClient
  ) { }

  findAll(){
    return this.httpClient.get<ReviewModel[]>(this.url+"findallreviews", {observe: "response"});
  }

  findAllUserReviews(user: UserModel){

    return this.httpClient.get<ReviewModel[]>(this.url+"getuserreviews/"+user.username, {observe: "response"});
  }

  findAllProductReviews(id: string){
    return this.httpClient.get<ReviewModel[]>(this.url+"findallbyproductid/"+id, {observe: "response"});

  }

}
