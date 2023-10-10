function convertNumberToCurrency(number) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(number);
}

export { convertNumberToCurrency };
