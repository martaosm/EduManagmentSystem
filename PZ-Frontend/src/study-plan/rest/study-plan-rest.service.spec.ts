import {TestBed} from '@angular/core/testing';

import {StudyPlanRestService} from './study-plan-rest.service';

describe('StudyPlanRestService', () => {
  let service: StudyPlanRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudyPlanRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
