package modelo.dto;

// Transporta datos de una compra entre capas
// Incluye descuento y total final según el tipo del cliente
public class ComprasDTO {

    private int id;
    private int idCliente;
    private int idProducto;
    private int cantidad;
    private double valorUnitario;
    private double totalSinDesc; // precio × cantidad sin descuento
    private double descuento; // valor en pesos del descuento
    private double totalFinal; // lo que realmente paga

    // Para mostrar en tablas (se llenan con JOIN)
    private String nombreCliente;
    private String nombreProducto;

    public ComprasDTO() {
    }

    public ComprasDTO(int idCliente, int idProducto, int cantidad,
            double valorUnitario, double totalSinDesc,
            double descuento, double totalFinal) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.totalSinDesc = totalSinDesc;
        this.descuento = descuento;
        this.totalFinal = totalFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int v) {
        this.idCliente = v;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int v) {
        this.idProducto = v;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double v) {
        this.valorUnitario = v;
    }

    public double getTotalSinDesc() {
        return totalSinDesc;
    }

    public void setTotalSinDesc(double v) {
        this.totalSinDesc = v;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double v) {
        this.descuento = v;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double v) {
        this.totalFinal = v;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String v) {
        this.nombreCliente = v;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String v) {
        this.nombreProducto = v;
    }

    // Mantiene compatibilidad: total = totalFinal
    public double getTotal() {
        return totalFinal;
    }
}