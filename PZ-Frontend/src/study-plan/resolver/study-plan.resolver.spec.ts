import {TestBed} from '@angular/core/testing';
import {ResolveFn} from '@angular/router';

import {studyPlanResolver} from './study-plan.resolver';

describe('studyPlanResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) =>
    TestBed.runInInjectionContext(() => studyPlanResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
