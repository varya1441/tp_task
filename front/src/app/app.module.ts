import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {LoginService} from "./services/login.service";
import {HttpClientModule} from "@angular/common/http";
import {GroupComponent} from "./group/group.component";
import {StudentService} from "./services/student.service";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MockServerService} from "./mock-server/mockServer.servise";
import {SignupComponent} from "./signup/signup.component";
import {GroupService} from "./services/group.service";
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    GroupComponent,
  ],
  imports: [
    RouterModule.forRoot([
      {path: '', component: LoginComponent},
      {path: 'signup', component: SignupComponent},
      {path: 'main/:studentLogin', component: GroupComponent},
    ]),
    BrowserModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MatToolbarModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule,
    MatCheckboxModule,
  ],
  providers: [LoginService, StudentService, MockServerService, GroupService],
  bootstrap: [AppComponent]
})
export class AppModule { }
