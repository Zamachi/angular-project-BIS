import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppComponent } from './app.component';
import { MaterialModule } from './material/material.module';
import { WelcomeComponent } from './welcome/welcome.component';


@NgModule({
  declarations: [ //NOTE: koje komponente su deo ovog modula?
    AppComponent, WelcomeComponent
  ],
  imports: [ //NOTE: koje module importujemo da bi koristili njihove funkcionalnosti?
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    FlexLayoutModule
  ], //NOTE: postoji i exports niz koji izlistava modules koje tekuci modul izvozi i omogucuje drugim modulima da koriste te elemente
  providers: [], //NOTE: koje servise koristi ovaj modul?
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA] //NOTE: ovo je neophodno ukoliko se koristi tzv. "Web component"
})
export class AppModule { }
