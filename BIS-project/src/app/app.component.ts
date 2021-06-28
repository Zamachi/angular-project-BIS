import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { LocalstorageService } from './services/localstorage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private authService: AuthService, private localStorageService: LocalstorageService, private router: Router) {}

  isLoggedIn$: any;
  isDarkTheme: boolean = false;

  title = 'BIS-project';

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isUserLoggedIn();

    this.getThemePreferenceFromLocalStorage();
  }

  toggleTheme() {
    this.isDarkTheme = !this.isDarkTheme;

    if (this.isDarkTheme)
      this.localStorageService.setLocalStorageItem("theme", "dark");
    else
      this.localStorageService.setLocalStorageItem("theme", "light");
  }

  logout() {
    this.localStorageService.clearLocalStorage();
    this.authService.logTheUserOut();
    this.isLoggedIn$ = this.authService.isUserLoggedIn();
    this.router.navigate(['/login']);
  }

  getThemePreferenceFromLocalStorage() {
    if(this.localStorageService.getLocalStorageItem("theme")) {
      if(this.localStorageService.getLocalStorageItem("theme")?.valueOf() == "light") {
        this.isDarkTheme = false;
      }
      else if (this.localStorageService.getLocalStorageItem("theme")?.valueOf() == "dark")
        this.isDarkTheme = true;
      else
        this.isDarkTheme = false;
    }
  }
}
