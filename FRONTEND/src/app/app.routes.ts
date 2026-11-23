import { Routes } from '@angular/router';
import { Listagem } from './pages/listagem/listagem';

export const routes: Routes = [
  { path: '', redirectTo: 'carteirinhas', pathMatch: 'full' },
  { path: 'carteirinhas', component: Listagem },
];