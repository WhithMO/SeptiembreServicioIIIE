package pe.idat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	private String nombreProducto;
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	@OneToOne
	private Proveedor proveedor;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})					// en una tabla MANY TO MANY, se puede crear la tabla DETALLE desde cualquier entidad de donde surga, en este caso, lo estamos creadno desde producto.
	@JoinTable(					// en esta anotacion se pone todo lo que generara la tabla DETALLE
			name = "producto_cliente",		// escribimos el nombre de la tabla a generar
			joinColumns = @JoinColumn(			
					name = "id_producto",			// aca se pone el nombre del PRIMER FK (con este nombre esta el atributo ID de producto en la BBDD)
					nullable = false,
					unique = true,
					foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references productos(id_producto)")
			),
			inverseJoinColumns = @JoinColumn(			
					name = "id_cliente",			// aca se pone el nombre del SEGUNDO FK
					nullable = false,
					unique = true,
					foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_cliente) references clientes(id_cliente)")
			)
			
	)
	private List<Cliente> clientes = new ArrayList<>();		// la variable CLIENTES se tendra que poner en el MAPPEDBY de MANYTOMANY del atributo de nombre PRODUCTOS de la clase CLIENTE
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
