/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Message } from '../pojo/Message';
import { StatusPageComponent } from '../pojo/StatusPageComponent';
import { StatusPageIncident } from '../pojo/StatusPageIncident';
import { StatusPageOrg } from '../pojo/StatusPageOrg';

const status_page_org_uri = '/status/page/org';

const status_page_component_uri = '/status/page/component';

const status_page_incident_uri = '/status/page/incident';

@Injectable({
  providedIn: 'root'
})
export class StatusPageService {
  constructor(private http: HttpClient) {}

  public saveStatusPageOrg(body: StatusPageOrg): Observable<Message<StatusPageOrg>> {
    return this.http.post<Message<StatusPageOrg>>(status_page_org_uri, body);
  }

  public getStatusPageOrg(): Observable<Message<StatusPageOrg>> {
    return this.http.get<Message<StatusPageOrg>>(status_page_org_uri);
  }

  public newStatusPageComponent(body: StatusPageComponent): Observable<Message<void>> {
    return this.http.post<Message<void>>(status_page_component_uri, body);
  }

  public editStatusPageComponent(body: StatusPageComponent): Observable<Message<void>> {
    return this.http.put<Message<void>>(status_page_component_uri, body);
  }

  public deleteStatusPageComponent(componentId: number): Observable<Message<void>> {
    return this.http.delete<Message<void>>(`${status_page_component_uri}/${componentId}`);
  }

  public getStatusPageComponents(): Observable<Message<StatusPageComponent[]>> {
    return this.http.get<Message<StatusPageComponent[]>>(status_page_component_uri);
  }

  public getStatusPageComponent(componentId: number): Observable<Message<StatusPageComponent>> {
    return this.http.get<Message<StatusPageComponent>>(`${status_page_component_uri}/${componentId}`);
  }

  public newStatusPageIncident(body: StatusPageIncident): Observable<Message<void>> {
    return this.http.post<Message<void>>(status_page_incident_uri, body);
  }

  public editStatusPageIncident(body: StatusPageIncident): Observable<Message<void>> {
    return this.http.put<Message<void>>(status_page_incident_uri, body);
  }

  public deleteStatusPageIncident(componentId: number): Observable<Message<void>> {
    return this.http.delete<Message<void>>(`${status_page_incident_uri}/${componentId}`);
  }

  public getStatusPageIncidents(): Observable<Message<StatusPageIncident[]>> {
    return this.http.get<Message<StatusPageIncident[]>>(status_page_incident_uri);
  }

  public getStatusPageIncident(incidentId: number): Observable<Message<StatusPageIncident>> {
    return this.http.get<Message<StatusPageIncident>>(`${status_page_incident_uri}/${incidentId}`);
  }
}
