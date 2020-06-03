import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Event} from "../objects/Event";
import {EventDto} from "../objects/EventDto";

@Injectable({providedIn: "root"})
export class EventService {
  selectedEvent: EventDto = new EventDto();
  createDto: EventDto = new EventDto();

  constructor(private http: HttpClient) {}

  public getAllEventsForGroup(groupName):Observable<Event[]>{
    return this.http.get<Event[]>('http://localhost:8080/events/all/' + groupName);
  }

  public createxEvent(groupName){
    return this.http.post('http://localhost:8080/events/' + groupName, {eventName: this.createDto.name, date: this.createDto.date});
  }

  public generateResult(eventName, groupName){
    return this.http.get('http://localhost:8080/events/' + eventName + '/result/' + groupName);
  }

  public updatexEvent(eventName){
    return this.http.put('http://localhost:8080/events/event/' + eventName,
       {eventName: this.selectedEvent.name, date: this.selectedEvent.date});
  }

  public getxPriority(eventName, studentLogin){
    return this.http.get('http://localhost:8080/student/' + eventName + '/priority/' + studentLogin);
  }

  public setxPriority(studentLogin, eventName, prior){
    let priority = {};
    for(let i = 0; i < prior.length; i++){
      priority[(i+1).toString()] = prior[i];
    }
    let dto = {eventName: eventName, priority: priority};
    console.log(dto);
    return this.http.put('http://localhost:8080/student/priority/u/' + studentLogin, {dto});
  }

}
