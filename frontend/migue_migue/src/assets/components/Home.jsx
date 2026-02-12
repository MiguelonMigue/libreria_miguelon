import React, { useState } from 'react'

const Home = () => {
  const [libros, setLibros] = useState([]);
  const [busqueda, setBusqueda] = useState('');
  const [modalInsertar, setModalInsertar] = useState(false);
  const [libroRegistrado, setLibroRegistrado] = useState({
    id: '',
    titulo: '',
    genero: '',
    autor: '',
    nacimiento: '',
    correo: '',
    editorial: '',
    anio_publicacion: ''
  })
  const getLibros = async () => {
    try{
      const response = await axios.get('http://localhost:8080/libro')
      setLibros(response.data);
    } catch(error){
      console.error(error.message);
    };
  }

  return (
    <div>
      <h1>Librería Miguelon</h1>
      <p>Estos son los libros que están a la venta!!!!!</p>
      
    </div>
  )
}

export default Home
