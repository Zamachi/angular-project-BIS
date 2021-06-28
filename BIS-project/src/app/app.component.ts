import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn$: any = false;
  isDarkTheme: boolean = false;

  title = 'BIS-project';

  toggleTheme() {
    this.isDarkTheme = !this.isDarkTheme;
  }
}
