import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StudyPlanTableComponent} from './study-plan-table.component';

describe('StudyPlanTableComponent', () => {
  let component: StudyPlanTableComponent;
  let fixture: ComponentFixture<StudyPlanTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyPlanTableComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(StudyPlanTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
