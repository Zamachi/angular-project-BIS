import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import '@google/model-viewer';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goNavigate(url: string) {
    this.router.navigate([url]);
  }

}
