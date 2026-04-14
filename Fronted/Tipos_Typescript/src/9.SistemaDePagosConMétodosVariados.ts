interface pagoEfetivo {
  metodo: "efetivo";
}

interface pagoTarjeta {
  metodo: "tarjeta";
  numeroTarjeta: string;
  cvv: string;
}

interface pagoTransferencia {
  metodo: "transferencia";
  banco: string;
  numeroCuenta: string;
}

type MetodoPago = pagoEfetivo | pagoTarjeta | pagoTransferencia;

function procesarPago(pago: MetodoPago) {
  if (pago.metodo === "efetivo") {
    console.log("Procesando pago en efectivo");
  } else if (pago.metodo === "tarjeta") {
    console.log(`Procesando pago con tarjeta número ${pago.numeroTarjeta}`);
  } else if (pago.metodo === "transferencia") {
    console.log(
      `Procesando pago por transferencia bancaria al banco ${pago.banco}`,
    );
  }
}

console.log("Procesando pagos...");
const pago1: MetodoPago = { metodo: "efetivo" };
procesarPago(pago1);
const pago2: MetodoPago = {
  metodo: "tarjeta",
  numeroTarjeta: "1234-5678-9012-3456",
  cvv: "123",
};
procesarPago(pago2);
const pago3: MetodoPago = {
  metodo: "transferencia",
  banco: "Bancolombia",
  numeroCuenta: "987654321",
};
procesarPago(pago3);
console.log("Pagos procesados correctamente");