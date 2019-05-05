import { Injectable } from '@angular/core';
import { NgxSoapService, Client, ISoapMethodResponse } from 'ngx-soap';

@Injectable({
  providedIn: 'root'
})
export class HttpManagerService {

  client: Client;
  
  constructor(private soap: NgxSoapService) {
    
    var wsdlOptions = {
      "overrideRootElement": {
        "namespace": "sch",
        "xmlnsAttributes": [{
          "name": "xmlns:sch",
          "value": "http://movies-soa.com/app"
        }]
      }
    };

    this.soap.createClient('/assets/movies.wsdl', wsdlOptions)
    .then(client => { this.client = client; })
  }

  addAuthHeader() {
    this.client.addSoapHeader({
      token: window.localStorage.getItem("token")
    });
  }
}
