import {PaymentNetwork} from './payment-network';

export interface Card{
  id?: number;
  card_number: string;
  expiration_date: Date;
  cvv: number;
  card_holder_name: string;
  payment_network: PaymentNetwork;
  state: string;
}
