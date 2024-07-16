package com.challengeliteratura.challengeliteratura.principal;

import com.challengeliteratura.challengeliteratura.entity.*;
import com.challengeliteratura.challengeliteratura.model.*;
import com.challengeliteratura.challengeliteratura.service.*;
import com.challengeliteratura.challengeliteratura.repository.*;
import com.challengeliteratura.challengeliteratura.service.ConsumoAPI;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<AutorDatos> autorDatos = new ArrayList<>();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private List<AutorEntity> autores;
    private Optional<AutorEntity> autorBuscado;

    public Principal (LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraElMenu() {
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
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }



    private LibroDatos getDatosLibro() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "title=" + tituloLibro);
        System.out.println(json);
        LibroDatos datos = conversor.obtenerDatos(json, LibroDatos.class);
        return datos;
    }

    private void buscarLibroWeb() {
        LibroDatos libroDatos = getDatosLibro();
        LibroDatos datos = getDatosLibro();
        LibroEntity libro = new LibroEntity(datos);
        libroRepositorio.save(libro);
        System.out.println(datos);
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
