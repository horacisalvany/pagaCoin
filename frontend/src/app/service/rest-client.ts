
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError as observableThrowError } from 'rxjs';
import { catchError, map, timeout } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
  })
export class RestClient {

    timeout = 60000;

    constructor(private http: HttpClient) {}

    call<T>(uri: string, method: string, body: any = null): Observable<T> {
        return this.http
            .request<T>(new HttpRequest(method, uri, body, httpOptions)).pipe(
                timeout(this.timeout),
                map(r => r['body']),
                catchError(this.handleError)
            );
    }

    private handleError(error: any) {
        const errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg);
        return observableThrowError(error);
    }
}
