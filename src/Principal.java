/**
 * Created by daniel on 31/03/16.
 */
public class Principal
{
    public static void main (String args[])
    {
        Servidor servidor = new Servidor();
        //servidor.iniciar();

        ServerController s1 =  new ServerController(0, servidor);
        ServerController s2 = new ServerController(1, servidor);
        s1.start();
        s2.start();
    }
}
