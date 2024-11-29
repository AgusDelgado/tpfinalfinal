package menues;
import exceptions.UserEliminatedException;
import model.*;

import java.util.List;
import java.util.Scanner;

public class Menu {

    // menu principal
    public void mainMenu (List<Users> listaUsuarios, List<Games> listaJuegos, Users usuarioLogeado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Inicio---------------");
            System.out.println("1) Mi Perfil");
            System.out.println("2) Juegos");
            System.out.println("9) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    profile(listaUsuarios, usuarioLogeado, listaJuegos);
                    break;
                case 2:
                    games(listaJuegos, usuarioLogeado);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (option != 9);
    }

    public void profile(List<Users> listaUsuarios, Users usuarioLogueado, List<Games> listaGame) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Perfil---------------");
            System.out.println("1) Ver perfil");
            System.out.println("2) Modificar datos");
            System.out.println("3) Eliminar cuenta");
            System.out.println("4) Ver comentarios");
            System.out.println("9) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    usuarioLogueado.showInfo();
                    break;
                case 2:
                    modifyData(listaUsuarios, usuarioLogueado);
                    break;
                case 3:
                    System.out.println("Eliminar cuenta");
                    listaUsuarios.remove((usuarioLogueado));
                    throw new UserEliminatedException(); // falla el programa
                case 4:
                    System.out.println("comentarios");
                    usuarioLogueado.showComments(listaGame, usuarioLogueado);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 9 || option == 3);
    }

    // modificacion de datos
    public void modifyData(List<Users> listaUsuarios, Users usuarioLogueado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Modificar datos---------------");
            System.out.println("1) Modificar nombre");
            System.out.println("2) Modificar contraseña");
            System.out.println("3) Modificar email");
            System.out.println("9) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese su nuevo nombre");
                    String username = scanner.next();
                    listaUsuarios.get(listaUsuarios.indexOf(usuarioLogueado)).setUsername(username);
                    break;
                case 2:
                    System.out.println("Ingrese su nueva contraseña");
                    String password = scanner.next();
                    listaUsuarios.get((listaUsuarios.indexOf(usuarioLogueado))).setPassword(password);
                    break;
                case 3:
                    System.out.println("Ingrese su nuevo email");
                    String email = scanner.next();
                    listaUsuarios.get((listaUsuarios.indexOf(usuarioLogueado))).setEmail(email);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 9);
    }

    //menu de juegos
    private void games(List<Games> listaJuegos, Users usuarioLogeado) {

        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------STORM---------------");
            System.out.println("1) Lista de Juegos");
            System.out.println("2) Juegos Favoritos");
            System.out.println("3) Buscar juego");
            System.out.println("9) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Lista de Juegos");
                    listaJuegos.forEach(Games::showInfo);
                    break;
                case 2:
                    System.out.println("Juegos Favoritos");
                    usuarioLogeado.showFavoriteGame(listaJuegos);
                    break;
                case 3:
                    System.out.println("Buscar juego");
                    System.out.println("Ingrese el nombre del juego a buscar:");
                    scanner.nextLine();
                    String gameName = scanner.nextLine();
                    Games juegoEncontado = listaJuegos.stream()
                            .filter(game -> game.getName().equalsIgnoreCase(gameName))
                            .findFirst().orElse(null);
                    if (juegoEncontado != null) {
                        // el juego si esta en la lista, se muesrtra
                        juegoEncontado.showInfo();
                        optionsGame(juegoEncontado, usuarioLogeado);
                    } else {
                        System.out.println("Juego no encontrado");
                    }
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (option != 9);
    }

    // menu de opciones de acciones en el juego
    public void optionsGame(Games juegoEncontrado, Users usuarioLogeado) {
        int option = 0;
        Scanner scanner = new Scanner(System.in);

        do{
            if (juegoEncontrado != null) {
                System.out.println("1) Agregar a favoritos");
                System.out.println("2) Comentar");
                System.out.println("9) Salir");
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        if(usuarioLogeado.getComments().contains(juegoEncontrado.getGameId())){
                            System.out.println("Este juego ya esta en favoritos");
                            break;
                        }
                        usuarioLogeado.addFavoriteGame(juegoEncontrado.getGameId());
                        System.out.println("Juego guardado en favorito");
                        break;
                    case 2:
                        System.out.println("Ingrese su comentario");
                        String comment = scanner.nextLine();
                        System.out.println("Cual es la calificacion del juego? (0-5)");
                        double rating = scanner.nextDouble();
                        System.out.println("Comentario guardado con exito");
                        Comments comments = new Comments(comment, usuarioLogeado.getUserId(), rating);
                        juegoEncontrado.addComment(comments);
                        usuarioLogeado.getComments().add(comments.getCommentId());
                        break;
                    // lista de juego encontrar el index de juego, pisar la informacion y setear el comentario guardado. Lo mismo es cuando se cambia el usuario

                    case 9:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
        }while (option != 9);
    }

    //menu de administrador
    public void adminMenu(List<Users> listaUsuarios, List<Games> listaJuegos, Users usuarioLogeado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Admin Menu---------------");
            System.out.println("1) Ver todos los usuarios");
            System.out.println("2) Ver todos los juegos");
            System.out.println("3) Agregar juego");
            System.out.println("4) Eliminar juego");
            System.out.println("5) Eliminar usuario");
            System.out.println("9) Salir");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    viewAllUsers(listaUsuarios);
                    break;
                case 2:
                    viewAllGames(listaJuegos);
                    break;
                case 3:
                    addGame(listaJuegos);
                    break;
                case 4:
                    deleteGame(listaJuegos);
                    break;
                case 5:
                    deleteUser(listaUsuarios);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 9);
    }

    private void viewAllUsers(List<Users> listaUsuarios) {
        System.out.println("Lista de Usuarios:");
        listaUsuarios.forEach(Users::showInfo);
    }

    private void viewAllGames(List<Games> listaJuegos) {
        System.out.println("Lista de Juegos:");
        listaJuegos.forEach(Games::showInfo);
    }

    private void addGame(List<Games> listaJuegos) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del juego:");
        String name = scanner.nextLine();
        System.out.println("Ingrese el género del juego:");
        Genre genre = getGenre();
        System.out.println("Ingrese el nombre del publicador:");
        String publisher = scanner.nextLine();
        System.out.println("Ingrese el precio del juego:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Ingrese la calificación del juego:");
        double rating = scanner.nextDouble();
        int id = listaJuegos.size() + 1;

        Games newGame = new Games(name, genre, id, publisher, price, rating);
        listaJuegos.add(newGame);
        System.out.println("Juego agregado exitosamente.");
    }

    public Genre getGenre(){
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Generos:");
            System.out.println("1: " + Genre.ACTION);
            System.out.println("2: " + Genre.ADVENTURE);
            System.out.println("3: " + Genre.ARCADE);
            System.out.println("4: " + Genre.RPG);
            System.out.println("5: " + Genre.STRATEGY);
            System.out.println("6: " + Genre.SIMULATION);
            System.out.println("7: " + Genre.SHOOTER);
            System.out.println("Ingrese el número correspondiente al género del juego: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    return Genre.ACTION;
                case 2:
                    return Genre. ADVENTURE;
                case 3:
                    return Genre.ARCADE;
                case 4:
                    return Genre.RPG;
                case 5:
                    return Genre.STRATEGY;
                case 6:
                    return Genre.SIMULATION;
                case 7:
                    return Genre.SHOOTER;
                default:
                    System.out.println("Opcion no valida"); // no entra esta opcion ROMPE SI PONES MAL EL NUM
                    break;
            }

        } while (option < 1 || option > 7);
        return null;
    }
    private void deleteGame(List<Games> listaJuegos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del juego a eliminar:");
        int id = scanner.nextInt();

        listaJuegos.removeIf(game -> game.getGameId() == id);
        System.out.println("Juego eliminado exitosamente.");
    }

    private void deleteUser(List<Users> listaUsuarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del usuario a eliminar:");
        int id = scanner.nextInt();

        listaUsuarios.removeIf(user -> user.getUserId() == id);
        System.out.println("Usuario eliminado exitosamente.");
    }

    //menu de developer
    public void devMenu(List<Games> listaJuegos, Users usuarioLogeado, Users logeado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Developer Menu---------------");
            System.out.println("1) Ver todos los juegos");
            System.out.println("2) Agregar juego");
            System.out.println("3) Modificar juego");
            System.out.println("9) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewAllGames(listaJuegos);
                    break;
                case 2:
                    addGame(listaJuegos);
                    break;
                case 3:
                    modifyGame(listaJuegos);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 5);
    }

    private void modifyGame(List<Games> listaJuegos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del juego a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Games gameToModify = listaJuegos.stream()
                .filter(game -> game.getGameId() == id)
                .findFirst()
                .orElse(null);

        if (gameToModify != null) {
            System.out.println("Ingrese el nuevo género del juego:");
            Genre genre = getGenre();
            System.out.println("Ingrese el nuevo precio del juego:");
            double price = scanner.nextDouble();

            gameToModify.setGenre(genre);
            gameToModify.setPrice(price);

            System.out.println("Juego modificado exitosamente.");
        } else {
            System.out.println("Juego no encontrado.");
        }
    }

    public void usertype (List<Users> listaUsuarios, List<Games> listaJuegos, Users usuarioLogeado) {

        if (usuarioLogeado instanceof Administrator) {
            adminMenu(listaUsuarios, listaJuegos, usuarioLogeado);

        } else if (usuarioLogeado instanceof Developers) {
            devMenu(listaJuegos, usuarioLogeado, usuarioLogeado);

        } else {
            mainMenu (listaUsuarios, listaJuegos, usuarioLogeado);
        }
    }


}

