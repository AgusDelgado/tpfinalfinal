package model;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class MenuLogin {

    // menu de inicio
    public Users homeMenu(List<Users> listaUsuarios) {

        Users usuarioLogeado;
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------Bienvenido a Storm---------------");
            System.out.println("1) Login");
            System.out.println("2) Registrarse");
            System.out.println("3) Salir");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    usuarioLogeado = login(listaUsuarios);
                    if (usuarioLogeado == null) {
                        System.out.println("Usuario no encontrado");
                        break;
                    }
                    return usuarioLogeado;
                case 2:
                    register(listaUsuarios);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 3);
        return null;


    }

    //Login de usuario
    public Users login(List<Users> listaUsuarios) {
        Scanner scanner = new Scanner(System.in);

        // menu de logueo
        System.out.println("Ingrese con su usernme");
        String username = scanner.nextLine();
        System.out.println("Ingrese con su contraseña");
        String password = scanner.nextLine();

        AtomicReference<Users> usuarioLoguedo = new AtomicReference<>();
        AtomicBoolean esta = new AtomicBoolean(false);

        listaUsuarios.forEach(usuario -> {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                esta.set(true);
                usuarioLoguedo.set(usuario);
            }
        });

        if (!esta.get()) {
            System.out.println("Usuario o contraseña incorrecta");
            return null;
        } else {
            System.out.println("Bienvenido a Storm");
            return usuarioLoguedo.get();
        }
    }

    // Registro de usuario nuevo
    public static void register(List<Users> listaUsuarios) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su mail");
        String mail = scanner.nextLine(); // agregar que si o si sea con un @ y/o con un .com
        System.out.println("Ingrese su username");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña"); // agregar que tenga al menos 8 caracteres
        String password = scanner.nextLine();
        Users user = new Users(username, password, mail);
        listaUsuarios.add(user);
        System.out.println("Usuario registrado con exito");
    }

    public void deleteAllUserData(List<Games> listaJuegos, Users usuarioLogeado) {
        usuarioLogeado.getComments().forEach(comentario -> {
            listaJuegos.forEach(juego -> {
                juego.getComments().forEach(comentarioJuego -> {
                    if (comentarioJuego.getCommentId() == comentario) {
                        juego.getComments().remove(comentarioJuego);
                    }
                });
            });
        });
    }

}