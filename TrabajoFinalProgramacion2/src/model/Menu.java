package model;

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
    public void mainMenu(List<Users> listaUsuarios, List<Games> listaJuegos, Users usuarioLogeado) {
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
                profile (listaUsuarios, usuarioLogeado);
                break;
            case 2:
               games (listaJuegos);
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
    public <listaUsuarios> void profile ( List<Users> listaUsuarios, Users usuarioLogueado) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Perfil---------------");
            System.out.println("1) Ver nombre");
            System.out.println("2) Modificar datos");
            System.out.println("3)Eliminar cuenta");
            option = scanner.nextInt();


            switch (option) {
                case 1:
                    System.out.println("Nombre: " + listaUsuarios.get(listaUsuarios.indexOf(usuarioLogueado)).getUsername());
                    break;
                case 2:
                    modifyData(listaUsuarios, usuarioLogueado);
                    break;
                case 3:
                    System.out.println("Eliminar cuenta");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 3);
    }
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
                    String username = scanner.nextLine();
                    listaUsuarios.get(listaUsuarios.indexOf(usuarioLogueado)).setUsername(username);
                    break;
                case 2:
                    System.out.println("Ingrese su nueva contraseña");
                    String password = scanner.nextLine();
                    listaUsuarios.get(0).setPassword(password);
                    break;
                case 3:
                    System.out.println("Ingrese su nuevo email");
                    String email = scanner.nextLine();
                    listaUsuarios.get(0).setEmail(email);
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
    private void games(List<Games> listaJuegos) {
    }
}

//System.out.println("Nombre: " + listaUsuarios.get(0).getUsername());