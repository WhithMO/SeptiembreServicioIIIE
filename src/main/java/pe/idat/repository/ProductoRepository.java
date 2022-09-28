package pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.model.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Integer>{

//	void guardar(Producto producto);
//	void actualizar(Producto producto);
//	void eliminar (Integer id);
//	List<Producto> listar();
//	Producto obtener(Integer id);
}
