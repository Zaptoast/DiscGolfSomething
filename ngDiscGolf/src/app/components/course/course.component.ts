import { CourseService } from './../../services/course.service';
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Router, ActivatedRoute } from '@angular/router';
import { RatingService } from 'src/app/services/rating.service';
import { Rating } from 'src/app/models/rating';
import { log } from 'util';



@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  courses: Course[] = [];

  ratingValue = 0;

  newRating: Rating = new Rating();

  newCourse: Course = new Course();

  selected: Course = null;

  editCourse: Course = null;

  constructor(private coursesvc: CourseService,
              private route: ActivatedRoute,
              private ratesvc: RatingService) { }

  rateCourse() {
    console.log(this.selected);
    console.log(' pre assign' + this.newRating);
    this.newRating.course = this.selected;
    this.newRating.value = this.ratingValue;
    console.log('post assign' + this.newRating);
    this.ratesvc.create(this.newRating).subscribe(
      data => {
        this.ratingValue = 0;
        this.newRating = null;
        this.reload();
      },

      err => console.error('Rating failed: ' + err)
    );

  }
  averageRating(course: Course) {
    let average = 0;
    course.ratings.forEach(element => {
      average += element.value;
    });
    average = average / course.ratings.length;
    return average;
  }

  updateCourse(course: Course) {
    this.coursesvc.update(course).subscribe(
      data => this.reload(),

      err => console.error('Observer got an error: ' + err)
    );
  }

  reload() {
    return this.coursesvc.index().subscribe(
      data => {
        this.courses = data;
      },

      err => console.error('Observer got an error: ' + err)
    );
  }

  ngOnInit() {
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      this.coursesvc.show(parseInt(this.route.snapshot.paramMap.get('id'))).subscribe(
        data => {
          this.selected = data;
        },
        err => console.error('error!' + err)
      );

      // this.selected = this.courses[this.route.snapshot.paramMap.get('id')];
    } else {
    this.reload();
    }
    this.newCourse = new Course();
  }

  setEditCourse() {
    this.editCourse = Object.assign({}, this.selected);
  }

  delete(course: Course) {
    this.coursesvc.destroy(course).subscribe(
      data => {
        this.reload();
      },
      err => console.error('Observer got an error: ' + err)
    );
  }

}
