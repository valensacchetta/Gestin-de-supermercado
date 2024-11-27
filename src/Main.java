import Clases.*;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int salir=0;
    public static void main(String[] args) {
       while(salir==0){
            try {
                Gestion_supermercado.iniciar();
                System.out.println();
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
                System.out.println();
            }
       }
    }
}