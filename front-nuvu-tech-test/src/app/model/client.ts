import {IdType} from './id-type';

export interface Client{
  id?: number;
  name: string;
  last_name: string;
  id_type: IdType;
  id_number: string;
  email: string;
  phone_number: string;
}
