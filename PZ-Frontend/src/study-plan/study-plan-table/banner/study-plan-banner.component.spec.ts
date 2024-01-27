import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StudyPlanBannerComponent} from './study-plan-banner.component';

describe('BanerComponent', () => {
  let component: StudyPlanBannerComponent;
  let fixture: ComponentFixture<StudyPlanBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyPlanBannerComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(StudyPlanBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
