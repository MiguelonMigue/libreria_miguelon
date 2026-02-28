import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Home = () => {
  const [libros, setLibros] = useState([]);
  const [busqueda, setBusqueda] = useState('');
  const [modalInsertar, setModalInsertar] = useState(false);
  
  // Estado inicial limpio
  const [libroRegistrado, setLibroRegistrado] = useState({
    titulo: '',
    genero: '',
    autor: '',
    correo: '',
    nacimiento: '',
    editorial: '',
    anio_publicacion: ''
  });

  const getLibros = async () => {
    try {
      const response = await axios.get('http://localhost:8080/libro');
      // Manejo de paginación (.content) detectado en tus capturas
      const data = response.data.content ? response.data.content : response.data;
      setLibros(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error("Error al obtener libros:", error.message);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLibroRegistrado(prev => ({ ...prev, [name]: value }));
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  // CONSTRUCCIÓN DEL OBJETO EXACTO PARA JAVA
  // El backend espera un objeto que coincida con DatosAutor
  const datosParaEnviar = {
    titulo: libroRegistrado.titulo,
    genero: libroRegistrado.genero,
    editorial: libroRegistrado.editorial,
    anio_publicacion: parseInt(libroRegistrado.anio_publicacion) || 0,
    
    // Este objeto debe coincidir con el record DatosAutor
    autor: {
      nombre: libroRegistrado.autor, // @NotBlank
      nacimiento: parseInt(libroRegistrado.nacimiento) || 0, // @NotNull
      
      // Este objeto debe coincidir con DatosUsuario
      usuario: {
        correo: libroRegistrado.correo // Aquí debe decir 'correo', no 'email'
      }
    }
  };

  console.log("Enviando al backend:", datosParaEnviar);

  try {
    const response = await axios.post('http://localhost:8080/libro', datosParaEnviar);
    console.log("Servidor respondió:", response.data);
    
    setModalInsertar(false);
    getLibros(); // Recargar la tabla
    alert("¡Libro, Autor y Usuario guardados exitosamente!");
  } catch (error) {
    // Si vuelve a salir 500, revisa la consola para ver el mensaje del servidor
    if (error.response) {
      console.error("Error del Servidor (500):", error.response.data);
      alert("Error 500: El servidor tuvo un problema. Revisa que el 'correo' sea válido.");
    } else {
      console.error("Error de red:", error.message);
    }
  }
};

  useEffect(() => {
    getLibros();
  }, []);

  const results = !busqueda
    ? libros
    : libros.filter((l) => l.titulo?.toLowerCase().includes(busqueda.toLowerCase()));

  return (
    
    <div className="container mt-4">
      <h2>Multimedia de libros</h2>
      <input
        className="form-control mb-3"
        placeholder="Buscar libro..."
        onChange={(e) => setBusqueda(e.target.value)}
      />
      <button className="btn btn-dark mb-3" onClick={() => setModalInsertar(true)}>
        Registrar libro
      </button>

      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Género</th>
            <th>Autor</th>
            <th>Editorial</th>
          </tr>
        </thead>
        <tbody>
          {results.length > 0 ? (
            results.map((libro) => (
              <tr key={libro.id}>
                <td>{libro.id}</td>
                <td>{libro.titulo}</td>
                <td>{libro.genero}</td>
                {/* Renderizado seguro de objeto Autor */}
                <td>{typeof libro.autor === 'object' ? libro.autor.nombre : libro.autor}</td>
                <td>{libro.editorial}</td>
              </tr>
            ))
          ) : (
            <tr><td colSpan="5" className="text-center">Cargando o sin datos...</td></tr>
          )}
        </tbody>
      </Table>

      <Modal isOpen={modalInsertar} toggle={() => setModalInsertar(false)}>
        <ModalHeader>Nuevo Libro</ModalHeader>
        <ModalBody>
          <div className="form-group">
            <label>Título</label>
            <input className="form-control" name="titulo" onChange={handleChange} />
            <label>Autor</label>
            <input className="form-control" name="autor" onChange={handleChange} />
            <label>Año Nacimiento </label>
            <input className="form-control" name="nacimiento" type="number" onChange={handleChange} />
            <label>Año Publicación </label>
            <input className="form-control" name="anio_publicacion" type="number" onChange={handleChange} />
            <label>Correo</label>
            <input className="form-control" name="correo" onChange={handleChange} />
            <label>Género</label>
            <input className="form-control" name="genero" onChange={handleChange} />
            <label>Editorial</label>
            <input className="form-control" name="editorial" onChange={handleChange} />
          </div>
        </ModalBody>
        <ModalFooter>
          <button className="btn btn-primary" onClick={handleSubmit}>Guardar</button>
          <button className="btn btn-secondary" onClick={() => setModalInsertar(false)}>Cancelar</button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default Home;