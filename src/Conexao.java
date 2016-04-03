import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by daniel on 01/04/16.
 */
public class Conexao
{
    private ServerSocket servSocket;
    private Socket socket;
    private ObjectInputStream sServIn;

    public Conexao()
    {
        this.servSocket = null;
        this.socket = null;
    }

    public Conexao(ServerSocket servSocket, Socket socket)
    {
        this.servSocket = servSocket;
        this.socket = socket;
    }

    public ServerSocket getServSocket()
    {
        return this.servSocket;
    }

    public void setServSocket(ServerSocket servSocket)
    {
        this.servSocket = servSocket;
    }

    public Socket getSocket()
    {
        return this.socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public ObjectInputStream getsServIn()
    {
        return this.sServIn;
    }

    public void setsServIn(ObjectInputStream sServIn)
    {
        this.sServIn = sServIn;
    }
}
