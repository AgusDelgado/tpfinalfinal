package menues;

import model.Games;
import model.Users;

import java.util.List;
import java.util.Objects;
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
            System.out.println("9) Salir");
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
        } while (option != 9);
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
        boolean checks = false;
        do{
            System.out.println("Ingrese su mail (Debe contener @ y .com)");
            String mail = scanner.nextLine();
            if(!mail.contains("@") && !mail.contains(".com")){
                System.out.println("Mail invalido");
            } else if (userExist(listaUsuarios, mail)) {
                System.out.println("Un usuario ya existe con esta direccion de email");
            }else {
                System.out.println("Ingrese su username");
                String username = scanner.nextLine();
                System.out.println("Ingrese su contraseña (debe tener al menos 8 caracteres)");
                String password = scanner.nextLine();
                if (password.length() < 8) {
                    System.out.println("Contraseña muy corta");
                } else {
                    Users user = new Users(username, password, mail);
                    listaUsuarios.add(user);
                    checks = true;
                }
            }
        }while (!checks);
        System.out.println("Usuario registrado con exito");
    }

    private static boolean userExist(List<Users> listaUsuarios, String mail) {
        AtomicBoolean esta = new AtomicBoolean(false);
        listaUsuarios.forEach(usuario -> {
            if (usuario.getEmail().equals(mail)) {
                esta.set(true);
            }
        });
        return esta.get();
    }

    public void deleteAllUserData(List<Games> listaJuegos, Users usuarioLogeado) {
        usuarioLogeado.getComments().forEach(comentario -> {
            listaJuegos.forEach(juego -> {
                juego.getComments().removeIf(comentarioJuego -> Objects.equals(comentarioJuego.getCommentId(), comentario));
            });
        });
    }

}