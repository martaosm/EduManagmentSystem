import { Routes } from '@angular/router';
import {ClassesComponent} from "../classes/classes.component";
import {ClassesResolver} from "../classes/resolver/classes.resolver";
import {StudentsResolver} from "../classes/resolver/students.resolver";
import {StudentsComponent} from "../classes/students/students.component";

export const routes: Routes = [
  {path: 'classes', component: ClassesComponent, resolve: {classes: ClassesResolver}},
  {path: 'classes/:groupCode', component: StudentsComponent, resolve: {students: StudentsResolver}},
];
