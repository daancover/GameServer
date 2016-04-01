import java.net.*;

public class Servidor
{
    private int vez;
    private int numJogadores;

    public int getVez()
    {
        return vez;
    }

    public void setVez(int vez)
    {
        this.vez = vez;
    }

    public Servidor getServidor()
    {
        return this;
    }

    public int getNumJogadores()
    {
        return numJogadores;
    }

    public void setNumJogadores(int numJogadores)
    {
        this.numJogadores = numJogadores;
    }

    // Passar a vez para o proximo jogador
    public int proximoJogador()
    {
        return (vez + 1) % numJogadores;
    }

    // Abrir porta para esperar por conexao
    public static void iniciar(int porta)
    {
        try
        {
            // Porta na qual o servidor irá "escutar"
            int ServerPort = porta;

            //ESTABELECE UM SERVIÇO UDP NA PORTA ESPECIFICADA
            DatagramSocket ds = new DatagramSocket(ServerPort);
            System.out.println("-S- Servidor estabelecendo servico UDP (P:"+ServerPort+")...");

            // CRIA UM PACOTE
            byte[] bytRec = new byte[100];
            // RECEBE DADOS DO CLIENTE
            DatagramPacket pktRec = new DatagramPacket(bytRec, bytRec.length);
            System.out.println("-S- Recebendo mensagem...");
            ds.receive(pktRec);

            //PROCESSA O PACOTE RECEBIDO
            bytRec = pktRec.getData();
            String strMsg = new String(bytRec, 0,bytRec.length);
            System.out.println("-S- Mensagem recebida: " + strMsg);

            // Pega dados do Cliente
            InetAddress ipRet = pktRec.getAddress();
            int portaRet = pktRec.getPort();

            //CRIA UM PACOTE de RETORNO
            String strMsgRet = "RETORNO - " + strMsg;
            byte[] bytRet = strMsgRet.getBytes();
            DatagramPacket pktEnv = new DatagramPacket(bytRet, bytRet.length, ipRet, portaRet);

            // E ENVIA PARA O CLIENTE
            System.out.println("-S- Enviando mensagem (IP:"+ipRet.getHostAddress()+" - P:"+portaRet+" )...:" + strMsgRet);
            ds.send(pktEnv);

            //FINALIZA O SERVICO UDP
            ds.close();
            System.out.println("-S- Conexao finalizada...");

        }
        catch(Exception e) //SE OCORRER ALGUMA EXCESSÃO, ENTÃO DEVE SER TRATADA (AMIGAVELMENTE)
        {
            System.out.println("-S- O seguinte problema ocorreu : \n" + e.toString());
        }
    }
}