import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//carga archivo
// carga liostas
        List<Users> listaUsuarios = new ArrayList<>();
        List<Games> listaJuegos = new ArrayList<>();

        listaUsuarios.add(new Users("user1", "password1", "user1@example.com"));


        listaUsuarios.add(new Users("user1", "password1", "user1@example.com"));
        listaJuegos.add(new Games("Game 1", Genre.ACTION, 1, "Publisher 1", 59.99, 4.5));

        MenuLogin loguin = new MenuLogin();
        Users usuarioLogeado = loguin.homeMenu(listaUsuarios);

        Menu menu = new Menu();
        menu.mainMenu(listaUsuarios, listaJuegos, usuarioLogeado);

        //guardar en archivos

    }
}