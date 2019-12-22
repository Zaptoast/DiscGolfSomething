import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private url = 'http://localhost:8082/api/courses';

  constructor(
    private http: HttpClient,
  ) { }

  index(): Observable<Course[]> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Course[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
}
create(course: Course) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.post<any>(this.url, course, httpOptions);
}
update(updatecourse: Course) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.put<any>(
      this.url + '/' + updatecourse.id,
      updatecourse,
      httpOptions
    );
}

destroy(course: Course) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.delete<any>(this.url + '/' + course.id, httpOptions);
}

show(id: number): Observable<Course> {
    return this.http.get<Course>(this.url + '/' + id);
}

}
