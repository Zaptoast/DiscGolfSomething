import { CourseService } from './services/course.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseComponent } from './components/course/course.component';
import { LengthPipe } from './pipes/length.pipe';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NewcourseComponent } from './components/newcourse/newcourse.component';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    AppComponent,
    CourseComponent,
    LengthPipe,
    AppComponent,
    HomeComponent,
    NavbarComponent,
    NotFoundComponent,
    NewcourseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    CommonModule,
  ],
  providers: [CourseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
