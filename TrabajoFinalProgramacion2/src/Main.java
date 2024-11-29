import exceptions.UserEliminatedException;
import model.*;
import model.Menu;
import utils.DataWrapper;
import utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) {

        List<Users> listaUsuarios;
        List<Games> listaJuegos;
        String filePath = "/Users/matt/Desktop/TrabajoFinalProgramacion2/src/data.json";

        try {
            DataWrapper data = JsonUtil.loadData(filePath);
            listaUsuarios = data.getUsers();
            listaJuegos = data.getGames();
            System.out.println("Datos cargados desde archivo JSON.");
        } catch (IOException e) {
            listaUsuarios = new ArrayList<>();
            listaJuegos = new ArrayList<>();
            System.out.println("No se pudo cargar el archivo JSON, se inicializarán listas vacías.");
        }

        MenuLogin login = new MenuLogin();
        Users usuarioLogeado = login.homeMenu(listaUsuarios);

        if(usuarioLogeado == null) {
            System.out.println("No se ha logueado ningún usuario.");
        }else {
            Menu menu = new Menu();
            try{
                menu.usertype(listaUsuarios, listaJuegos, usuarioLogeado);

            }catch (UserEliminatedException e){
                login.deleteAllUserData(listaJuegos, usuarioLogeado);
                System.out.println("Tu usuario fue eliminado.");
            }
        }

        try {
            JsonUtil.saveData(listaUsuarios, listaJuegos, filePath);
            System.out.println("Datos guardados en archivo JSON.");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON.");
        }

    }
}