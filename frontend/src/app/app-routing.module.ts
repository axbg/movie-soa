import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';

const routes: Routes = [
  { path: 'app', loadChildren: './tabs/tabs.module#TabsPageModule', canActivate: [AuthGuardService] },
  {
    path: 'movie/:id', loadChildren: './movie-detail/movie-detail.module#MovieDetailPageModule',
    canActivate: [AuthGuardService]
  },
  { path: '', loadChildren: './login/login.module#LoginPageModule' }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
