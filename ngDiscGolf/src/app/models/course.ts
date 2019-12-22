import { Address } from './address';
import { Rating } from './rating';

export class Course {
  id: number;
  name: string;
  length: number;
  description: string;
  address: Address;
  ratings: Rating[];
}
