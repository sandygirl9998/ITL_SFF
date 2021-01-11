import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  iwillhide=false;
  title = 'SchemeForFarmers';
  logoutbtn=true;
  loginbtn = false;

  constructor(private router: Router) { }

  ngOnInit() : void{
    if(sessionStorage.length===0)
    {
      this.logoutbtn = false;
      this.loginbtn = true;
    }
    else{
      this.loginbtn = false;
      this.logoutbtn = true;

    }
  }

  logout()
  {
    sessionStorage.clear()
    this.router.navigate(['/']);
  }

}
