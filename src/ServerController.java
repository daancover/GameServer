/**
 * Created by daniel on 31/03/16.
 */
public class ServerController extends Thread
{
    int id;
    private Servidor servidor;
    private Conexao conexao;

    public ServerController(int id, Servidor servidor)
    {
        this.id = id;
        this.servidor = servidor;
    }

    public Conexao getConexao()
    {
        return conexao;
    }

    public void setConexao(Conexao conexao)
    {
        this.conexao = conexao;
    }

    public void run()
    {
        servidor.iniciar(id, this);
    }
}
