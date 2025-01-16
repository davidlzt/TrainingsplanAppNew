import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthModuleComponent } from './auth.module.component';

describe('AuthModuleComponent', () => {
  let component: AuthModuleComponent;
  let fixture: ComponentFixture<AuthModuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthModuleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AuthModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
