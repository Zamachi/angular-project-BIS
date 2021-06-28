import { Injectable } from '@angular/core';
import { UserModel } from '../models/userModel';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: UserModel;

  constructor() { }

  getCurrentUser(): UserModel {
    return this.user;
  }

  setCurrentUser(user: UserModel) {
    this.user = user;
  }
}
