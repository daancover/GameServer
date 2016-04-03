package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 31/03/16.
 */
public class Principal
{
    private static final int MAXPLAYERS = 10;
    private static List<ServerController> threads = new ArrayList<>();

    public static void main (String args[])
    {
        Servidor servidor = new Servidor();

        ServerController s;

        for(int i = 0; i < MAXPLAYERS; i++)
        {
            s = new ServerController(i, servidor);
            threads.add(s);
            s.start();
        }
    }
}
