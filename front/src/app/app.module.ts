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
import {EventService} from "./services/event.service";
import {MatMenuModule} from "@angular/material/menu";
import {WallComponent} from "./wall.component/wall.component";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatBadgeModule} from "@angular/material/badge";
import {ClipboardModule} from "@angular/cdk/clipboard";
import {MatSortModule} from "@angular/material/sort";
import {EventCreateComponent} from "./event-create/event-create.component";
import {MatDialogModule} from "@angular/material/dialog";
import {MatSliderModule} from "@angular/material/slider";
import {PriorComponent} from "./prior/prior.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {TransferService} from "./services/transfer.service";
import {EventEditComponent} from "./event-edit/event-edit.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    GroupComponent,
    WallComponent,
    EventCreateComponent,
    PriorComponent,
    EventEditComponent,
  ],
  entryComponents: [
    EventCreateComponent,
    EventEditComponent,
  ],
  imports: [
    RouterModule.forRoot([
      {path: '', component: LoginComponent},
      {path: 'signup', component: SignupComponent},
      {path: 'main/:studentLogin', component: GroupComponent},
      {path: 'notconfirmed', component: WallComponent},
      {path: 'signupsuccess', component: WallComponent},
      {path: 'prior/:eventName/:studentLogin', component: PriorComponent},
    ]),
    BrowserModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatBadgeModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MatSortModule,
    MatToolbarModule,
    MatTableModule,
    MatIconModule,
    MatMenuModule,
    MatPaginatorModule,
    MatCheckboxModule,
    ClipboardModule,
    MatDialogModule,
    MatSliderModule,
    MatFormFieldModule,
  ],
  exports: [
    MatSortModule,
  ],
  providers: [LoginService, StudentService, MockServerService, GroupService, EventService, TransferService],
  bootstrap: [AppComponent]
})
export class AppModule { }
