import {Component} from "@angular/core";
import {MatDialogRef} from "@angular/material/dialog";
import {EventService} from "../services/event.service";
import {EventDto} from "../objects/EventDto";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css'],
})
export class EventCreateComponent{
  data: EventDto = new EventDto();

  constructor(public dialogRef: MatDialogRef<EventCreateComponent>,
              public eventService: EventService) {
    dialogRef.disableClose = true;
    this.data.date = null;
    this.data.name = null;
  }

  submit() {
    this.eventService.createDto = JSON.parse(JSON.stringify(this.data));
  }

  cancel(): void {
    this.dialogRef.close("cancelled");
  }
}
