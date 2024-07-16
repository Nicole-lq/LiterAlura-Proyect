package com.challengeliteratura.challengeliteratura.client;

import java.util.List;
import java.util.Scanner;

import com.challengeliteratura.challengeliteratura.entity.AutorEntity;
import com.challengeliteratura.challengeliteratura.entity.LibroEntity;
import com.challengeliteratura.challengeliteratura.model.LibroDatos;
import com.challengeliteratura.challengeliteratura.repository.AutorRepository;
import com.challengeliteratura.challengeliteratura.repository.LibroRepository;
import com.challengeliteratura.challengeliteratura.service.ConsumoAPI;
import com.fasterxml.jackson.databind.ObjectMapper; // Importa ObjectMapper aquí
import java.io.IOException;

public class ClienteLiteratura {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ObjectMapper objectMapper = new ObjectMapper(); // Define ObjectMapper aquí

    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public ClienteLiteratura(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
					¿Qué deseas hacer? Escribe el número de tu selección:
						1.- Buscar un libro por título
						2.- Obtener una lista de libros registrados
						3.- Obtener una lista de autores registrados
						4.- Obtener una lista de autores vivos en un determinado año
						5.- Obtener una lista de libros de determinado idioma
						0 - Salir
					""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivo();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Adios, Vuelva Pronto...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibroWeb() {
        LibroDatos libroDatos = getDatosLibro();

        if (libroDatos != null) {
            LibroEntity libroEntity = convertirALibroEntity(libroDatos);
            libroEntity = libroRepositorio.save(libroEntity);
            System.out.println("Libro guardado en la base de datos:\n" + libroEntity);
        } else {
            System.out.println("No se encontraron libros con ese título.");
        }
    }

    private LibroDatos getDatosLibro() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Buscando libros con título: " + titulo);
        String URL_BASE = "https://gutendex.com/books/";
        var json = consumoApi.obtenerDatos(URL_BASE + titulo); // Solo pasa el URL como String
        System.out.println("Respuesta JSON obtenida:\n" + json);
        try {
            return objectMapper.readValue(json, LibroDatos.class); // Usa objectMapper para convertir JSON a LibroDatos
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir JSON a objeto LibroDatos: " + e.getMessage(), e);
        }
    }

    private LibroEntity convertirALibroEntity(LibroDatos libroDatos) {
        String titulo = libroDatos.titulo();
        String lenguaje = libroDatos.lenguaje();
        Integer descargas = libroDatos.descargas();
        AutorEntity autor = null;
        return new LibroEntity(libroDatos);
    }

    private void buscarLibros() {
        List<LibroEntity> libros = libroRepositorio.findAll();

        if (!libros.isEmpty()) {
            System.out.println("\n\n---------- Listado de Libros Registrados -------\n");
            for (LibroEntity libro : libros) {
                System.out.println(libro);
            }
            System.out.println("\n-------------------------\n\n");
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON LIBROS REGISTRADOS ---- \n\n");
        }
    }

    private void buscarAutores() {
        List<AutorEntity> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            System.out.println("\n\n---------- Listado de Autores Registrados -------\n");
            for (AutorEntity autor : autores) {
                System.out.println(autor);
            }
            System.out.println("\n-------------------------\n\n");
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON AUTORES REGISTRADOS ---- \n\n");
        }
    }

    private void buscarAutoresVivo() {
        System.out.println("Escribe el año para filtrar autores vivos: ");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<AutorEntity> autores = autorRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            System.out.println("\n\n---------- Listado de Autores Vivos en " + anio + " -------\n");
            for (AutorEntity autor : autores) {
                System.out.println(autor);
            }
            System.out.println("\n-------------------------\n\n");
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON AUTORES VIVOS EN " + anio + " ---- \n\n");
        }
    }

    private void buscarPorIdiomas() {
        var menu = """
				Seleccione un Idioma:
					1.- Español
					2.- Inglés
				""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String seleccion = "";

        if (idioma == 1) {
            seleccion = "es";
        } else if (idioma == 2) {
            seleccion = "en";
        }

        List<LibroEntity> libros = libroRepositorio.findForLanguaje(seleccion);

        if (!libros.isEmpty()) {
            System.out.println("\n\n---------- Listado de Libros en Idioma " + seleccion + " -------\n");
            for (LibroEntity libro : libros) {
                System.out.println(libro);
            }
            System.out.println("\n-------------------------\n\n");
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON LIBROS EN IDIOMA " + seleccion + " ---- \n\n");
        }
    }
}
