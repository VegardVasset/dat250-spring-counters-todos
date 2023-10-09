export class Todo {
  id: number;
  description: string;
  summary: string;

  constructor(id: number, description: string, summary: string) {
    this.id = id;
    this.description = description;
    this.summary = summary;
  }
}

