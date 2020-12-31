export interface CreateCardRequest{
  userId: number|undefined;
  cardNumber: string;
  expirationDate: string;
  cvv: number;
  cardHolderName: string;
  paymentNetworkId: number;
  state: string;
}
