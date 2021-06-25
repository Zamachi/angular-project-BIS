import { Component } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import "@google/model-viewer";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BIS-project';

  constructor(private store: AngularFirestore){
            //NOTE: inicijalizuje Firestore servis
  }

}
