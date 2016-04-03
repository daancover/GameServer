import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        for(int i = 0; i < MAXPLAYERS; i++)
        {
            final ServerController s;
            s = new ServerController(i, servidor);
            threads.add(s);
            s.start();
        }
    }
}
