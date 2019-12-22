import { TestBed } from '@angular/core/testing';

import { AmmenityService } from './ammenity.service';

describe('AmmenityService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AmmenityService = TestBed.get(AmmenityService);
    expect(service).toBeTruthy();
  });
});
