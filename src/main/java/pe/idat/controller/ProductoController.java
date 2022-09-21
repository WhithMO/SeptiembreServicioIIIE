package pe.idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.model.Producto;
import pe.idat.repository.ProductoRepository;
import pe.idat.service.ProductoService;

@RestController
@RequestMapping("/producto/v1")
public class ProductoController {

	@Autowired
	private ProductoService repository;
	//private ProductoRepository repository;
	
	@Autowired
	@RequestMapping(path = "/listar", method = RequestMethod.GET)		// esto es para poner una url, un sub recurso tiene q estar entre los parentesis.
	public ResponseEntity<List<Producto>> listar() {
		return new ResponseEntity<List<Producto>>(repository.listar(), HttpStatus.OK);	// cuando es de tipo GET, osea cuando se obtendra un OBJETO, lo que va en el parametro anterior al HTTPSTATUS, sera el OBJETO que estamos trayendo u obteniendo. 
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Producto producto) {	// cuando un metodo nos trae un objeto para poder usarlo (interprete y trabajarlo) se usa la anotacion @RequestBody
		repository.guardar(producto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/detalle/{id}", method = RequestMethod.GET)
	public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {	// El @PathVariable es para poder usar una VARIABLE que esta en el PATH.
		Producto producto = repository.obtener(id);
		if (producto != null) {
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Producto producto) {
		Producto prod = repository.obtener(producto.getIdProducto());
		
		if (prod != null) {
			repository.actualizar(producto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		Producto producto = repository.obtener(id);
		
		if (producto != null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
