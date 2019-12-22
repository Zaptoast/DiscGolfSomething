import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Rating } from '../models/rating';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private url = 'http://localhost:8082/api/ratings';

  constructor(
    private http: HttpClient,
  ) { }

  create(rating: Rating) {
    console.log('in service create' + rating);
    console.log(rating);

    const   httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<any>(this.url, rating, httpOptions);
  }


}
