import {Component} from "@angular/core";
import {MatDialogRef} from "@angular/material/dialog";
import {EventService} from "../services/event.service";
import {EventDto} from "../objects/EventDto";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css'],
})
export class EventEditComponent{
  data: EventDto = new EventDto();

  constructor(public dialogRef: MatDialogRef<EventEditComponent>,
              public eventService: EventService) {
    dialogRef.disableClose = true;
    this.data = JSON.parse(JSON.stringify(this.eventService.selectedEvent));
    //this.data.date = this.eventService.selectedEvent.date;
    //this.data.name = this.eventService.selectedEvent.name;
  }

  submit() {
    this.eventService.selectedEvent = JSON.parse(JSON.stringify(this.data));
  }

  cancel(): void {
    this.dialogRef.close("cancelled");
  }
}
