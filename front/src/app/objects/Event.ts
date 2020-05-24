import {Group} from "./Group";
import {Priority} from "./Priority";
import {Result} from "./Result";

export class Event{
  id: string;
  eventName: string;
  date: string;
  group: Group;
  priorities: Priority[];
  result: Result;
}
