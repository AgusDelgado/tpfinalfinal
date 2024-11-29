package model;
import exceptions.UserEliminatedException;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Users> users;
    private List<Games> games;

    public void cargaListas() {

        // Create 5 games
        games.add(new Games("Game 1", Genre.ACTION, 1, "Publisher 1", 59.99, 4.5));
        games.add(new Games("Game 2", Genre.ADVENTURE, 2, "Publisher 2", 49.99, 4.0));
        games.add(new Games("Game 3", Genre.RPG, 3, "Publisher 3", 39.99, 3.5));
        games.add(new Games("Game 4", Genre.SIMULATION, 4, "Publisher 4", 29.99, 4.8));
        games.add(new Games("Game 5", Genre.STRATEGY, 5, "Publisher 5", 19.99, 4.2));

        users.add(new Users("user1", "password1", "user1@example.com"));
        users.add(new Users("user2", "password2", "user2@example.com"));
        users.add(new Users("user3", "password3", "user3@example.com"));
        users.add(new Developers("developer", "devpass", "dev@example.com", "developer", 0));
        users.add(new Administrator("admin", "adminpass", "admin@example.com"));

        // Create 10 comments
        Comments comment1 = new Comments("Amazing game!", users.get(0).getUserId(), 3.0);
        Comments comment2 = new Comments("Really enjoyed it.", users.get(1).getUserId(), 1.0);
        Comments comment3 = new Comments("Could use some improvements.", users.get(2).getUserId(), 2.0);
        Comments comment4 = new Comments("Loved the graphics!", users.get(3).getUserId(), 2.0);
        Comments comment5 = new Comments("Not my type.", users.get(4).getUserId(), 4.0);
        Comments comment6 = new Comments("Very engaging.", users.get(0).getUserId(), 5.0);
        Comments comment7 = new Comments("Had a lot of fun.", users.get(1).getUserId(), 3.0);
        Comments comment8 = new Comments("Too many bugs.", users.get(2).getUserId(), 2.0);
        Comments comment9 = new Comments("Great storyline.", users.get(3).getUserId(), 4.0);
        Comments comment10 = new Comments("Would recommend.", users.get(4).getUserId(), 4.0);

        // Assign comments to games
        games.get(0).addComment(comment1);
        games.get(0).addComment(comment2);
        games.get(1).addComment(comment3);
        games.get(1).addComment(comment4);
        games.get(2).addComment(comment5);
        games.get(2).addComment(comment6);
        games.get(3).addComment(comment7);
        games.get(3).addComment(comment8);
        games.get(4).addComment(comment9);
        games.get(4).addComment(comment10);


        }
    public void mainMenu (List<Users> listaUsuarios, List<Games> listaJuegos, Users usuarioLogeado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Inicio---------------");
            System.out.println("1) Mi Perfil");
            System.out.println("2) Juegos");
            System.out.println("3) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    profile(listaUsuarios, usuarioLogeado, listaJuegos);
                    break;
                case 2:
                    games(listaJuegos, usuarioLogeado);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (option != 3);
    }

    public <listaUsuarios> void profile(List<Users> listaUsuarios, Users usuarioLogueado, List<Games> listaGame) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Perfil---------------");
            System.out.println("1) Ver perfil");
            System.out.println("2) Modificar datos");
            System.out.println("3) Eliminar cuenta");
            System.out.println("4) Ver comentarios");
            System.out.println("5) Salir");
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
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 5 || option == 3);
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
            System.out.println("4) Salir");
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
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 4);
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
            System.out.println("4) Salir");
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
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (option != 4);
    }

    // menu de opciones de acciones en el juego
    public void optionsGame(Games juegoEncontado, Users usuarioLogeado) {

        int option;
        Scanner scanner = new Scanner(System.in);

        if (juegoEncontado != null) {
            System.out.println("1) Agregar a favoritos");
            System.out.println("2) Comentar");
            System.out.println("3) Salir");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    usuarioLogeado.addFavoriteGame(juegoEncontado.getGameId());
                    System.out.println("Juego guardado en favorito");
                    break;
                case 2:
                    System.out.println("Ingrese su comentario");
                    scanner.nextLine(); // Consumir la nueva línea pendiente
                    String comment = scanner.nextLine();
                    System.out.println("Cual es la calificacion del juego? (0-5)");
                    double rating = scanner.nextDouble();
                    System.out.println("Comentario guardado con exito");
                    Comments comments = new Comments(comment, usuarioLogeado.getUserId(), rating);
                    juegoEncontado.addComment(comments);
                    usuarioLogeado.getComments().add(comments.getCommentId());
                    break;
                // lista de juego encontrar el index de juego, pisar la informacion y setear el comentario guardado. Lo mismo es cuando se cambia el usuario

                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
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
            System.out.println("6) Salir");
            option = scanner.nextInt();

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
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 6);
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
        String name = scanner.next();
        System.out.println("Ingrese el género del juego:");
        String genre = scanner.next();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del publicador:");
        String publisher = scanner.nextLine();
        System.out.println("Ingrese el precio del juego:");
        double price = scanner.nextDouble();
        System.out.println("Ingrese la calificación del juego:");
        double rating = scanner.nextDouble();
        int id = listaJuegos.size() + 1;

        Games newGame = new Games(name, Genre.valueOf(genre.toUpperCase()), id, publisher, price, rating);
        listaJuegos.add(newGame);
        System.out.println("Juego agregado exitosamente.");
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
            System.out.println("4) Salir");
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
                case 4:
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
            String genre = scanner.next();
            System.out.println("Ingrese el nuevo precio del juego:");
            double price = scanner.nextDouble();

            gameToModify.setGenre(Genre.valueOf(genre.toUpperCase()));
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

