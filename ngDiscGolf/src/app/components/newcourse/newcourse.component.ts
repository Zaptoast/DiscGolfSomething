import { AddressService } from './../../services/address.service';
import { CourseService } from './../../services/course.service';
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-newcourse',
  templateUrl: './newcourse.component.html',
  styleUrls: ['./newcourse.component.css']
})
export class NewcourseComponent implements OnInit {

  newCourse: Course = new Course();
  newAddress: Address = new Address();

  constructor(private coursesvc: CourseService, private router: Router, private addsvc: AddressService ) { }

  addNewCourse() {
    console.log(this.newCourse);
    this.coursesvc.create(this.newCourse).subscribe(
      data => {
        console.log('*******');

        console.log(data);
        const tempCourse = data;
        this.coursesvc.index();
        this.router.navigateByUrl('course/');
      },
      err => console.error('Observer got an error: ' + err)
      );
      this.newCourse = new Course();
    }
    addNewAddress() {
      this.addsvc.create(this.newAddress).subscribe(
        data => {
        const tempAddress = data;
        console.log(tempAddress.id);
        this.newCourse.address = tempAddress;

        console.log('address created');
        console.log(this.newCourse.address);
        console.log(this.newAddress.id);
        console.log(this.newCourse);

        this.addNewCourse();
      },
      err => console.error('error creating address: ' + err)
      );
  }

  ngOnInit() {
  }

}
