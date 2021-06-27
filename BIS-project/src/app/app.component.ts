import { Component } from '@angular/core';
import "@google/model-viewer";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BIS-project';

  constructor(){
            //NOTE: inicijalizuje Firestore servis
  }

}
