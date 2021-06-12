import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';

//NOTE: import komponenti koje rutiramo

const routes: Routes = [
  //NOTE: sablon pisanja path: 'URL_putanja', component: ime_komponente
  {path: '',component: WelcomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
