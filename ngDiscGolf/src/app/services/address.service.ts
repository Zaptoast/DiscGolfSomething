import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Address } from '../models/address';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private url = 'http://localhost:8082/api/addresses';

  constructor(
    private http: HttpClient,
  ) { }

  index(): Observable<Address[]> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Address[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
}
create(address: Address) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.post<any>(this.url, address, httpOptions);
}
update(updateaddress: Address) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.put<any>(
      this.url + '/' + updateaddress.id,
      updateaddress,
      httpOptions
    );
}

destroy(address: Address) {
  const   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.delete<any>(this.url + '/' + address.id, httpOptions);
}

show(id: number): Observable<Address> {
    return this.http.get<Address>(this.url + '/' + id);
}

}
