/**
 * Created by daniel on 31/03/16.
 */
public class ServerController extends Thread
{
    int id;
    private Servidor servidor;

    public ServerController(int id, Servidor servidor)
    {
        this.id = id;
        this.servidor = servidor;
    }

    public void run()
    {
        servidor.iniciar(6001 + id);
    }
}
