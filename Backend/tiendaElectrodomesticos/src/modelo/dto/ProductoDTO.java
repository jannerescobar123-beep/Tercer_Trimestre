package modelo.dto;

/**
 * ============================================================
 *  CLASE: ProductoDTO  (Data Transfer Object)
 *  PAQUETE: modelo.dto
 * ============================================================
 *  PROPÓSITO:
 *    Transporta los datos de un producto (electrodoméstico)
 *    entre las capas del programa sin lógica de negocio.
 *
 *  FLUJO:
 *    Base de datos → ProductoDAO llena ProductoDTO
 *                  → pasa al Controlador
 *                  → Controlador lo muestra en la GUI
 * ============================================================
 */
public class ProductoDTO {

    // ── Atributos (corresponden a columnas de la tabla productos) ──
    private int    id;
    private String nombre;
    private double precio;
    private int    stock;

    // ── Constructores ──────────────────────────────────────
    public ProductoDTO() {}

    public ProductoDTO(int id, String nombre, double precio, int stock) {
        this.id     = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    public ProductoDTO(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    // ── Getters y Setters ──────────────────────────────────
    public int    getId()                    { return id; }
    public void   setId(int id)              { this.id = id; }

    public String getNombre()                { return nombre; }
    public void   setNombre(String nombre)   { this.nombre = nombre; }

    public double getPrecio()                { return precio; }
    public void   setPrecio(double precio)   { this.precio = precio; }

    public int    getStock()                 { return stock; }
    public void   setStock(int stock)        { this.stock = stock; }

    // toString: se muestra en el JComboBox de la ventana de compras
    @Override
    public String toString() {
        return id + " - " + nombre + " ($" + String.format("%,.0f", precio) + ")";
    }
}