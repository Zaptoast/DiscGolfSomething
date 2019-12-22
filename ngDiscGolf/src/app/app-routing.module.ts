import { HomeComponent } from './components/home/home.component';
import { CourseComponent } from './components/course/course.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NewcourseComponent } from './components/newcourse/newcourse.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'course', component: CourseComponent },
  { path: 'course/:id', component: CourseComponent },
  { path: 'new', component: NewcourseComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
