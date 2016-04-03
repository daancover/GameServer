/**
 * Created by daniel on 31/03/16.
 */

public class Jogador extends Thread
{
    private int id;
    private Servidor servidor;

    public Jogador(int id, Servidor servidor)
    {
        this.id = id;
        this.servidor = servidor;
    }

    public Servidor getServidor()
    {
        return servidor;
    }

    public void setServidor(Servidor servidor)
    {
        this.servidor = servidor;
    }

    public void run()
    {
        try
        {
            // Verifica se eh a vez do jogador
            if(servidor.getVez() == id)
            {
                // Desenhar
            }

            else
            {
                Thread.sleep(500);
            }
        }

        catch(InterruptedException e)
        {
        }
    }
}
