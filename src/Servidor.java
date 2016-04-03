import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Servidor
{
    private int vez;
    private int numJogadores;
    private boolean jogando = true;
    private String palavra = "mesa";
    private List<Conexao> conexoes = new ArrayList<>();
    private List<ServerController> threads = new ArrayList<>();

    public Servidor()
    {
        this.vez = 0;
        this.numJogadores = 0;

        for(int i = 0; i < 10; i++)
        {
            conexoes.add(new Conexao());
        }
    }

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

    public void fecharConexoes()
    {
        for (int i = 0; i < numJogadores; i++)
        {
            try
            {
                conexoes.get(i).setSocket(conexoes.get(i).getServSocket().accept());
                //conexoes.get(i).setsServIn(new ObjectInputStream(conexoes.get(i).getSocket().getInputStream()));
                conexoes.get(i).getsServIn().close();
                conexoes.get(i).getServSocket().close();
                conexoes.get(i).getSocket().close();
            }

            catch(Exception e) {
            }
        }
    }

    // Abrir porta para esperar por conexao
    public void iniciar(int id, ServerController thread)
    {
        numJogadores++;

        threads.add(thread);

        while(jogando)
        {
            try
            {
                int portaServidor = 7000 + id;

                //INICIALIZA UM SERVIÇO DE ESCUTA POR CONEXÕES NA PORTA ESPECIFICADA
                System.out.println(" -S- Aguardando conexao (P:" + portaServidor + ")...");
                conexoes.get(id).setServSocket(new ServerSocket(portaServidor));
                conexoes.get(id).getServSocket().setSoTimeout(500);

                //ESPERA (BLOQUEADO) POR CONEXÕES
                conexoes.get(id).setSocket(conexoes.get(id).getServSocket().accept());
                //RECEBE CONEXÃO E CRIA UM NOVO CANAL (p) NO SENTIDO CONTRÁRIO (SERVIDOR -> CLIENTE)
                System.out.println(" -S- Conectado ao cliente ->" + conexoes.get(id).getSocket().toString());

                //CRIA UM PACOTE DE ENTRADA PARA RECEBER MENSAGENS, ASSOCIADO À CONEXÃO (p)
                conexoes.get(id).setsServIn(new ObjectInputStream(conexoes.get(id).getSocket().getInputStream()));
                System.out.println(" -S- Recebendo mensagem...");
                Object msgIn = conexoes.get(id).getsServIn().readObject(); //ESPERA (BLOQUEADO) POR UM PACOTE
                System.out.println(" -S- Recebido: " + msgIn.toString());

                String resposta = "errou";

                if(msgIn.toString().equals(palavra))
                {
                    resposta = "acertou";
                    jogando = false;
                }

                //CRIA UM PACOTE DE SAÍDA PARA ENVIAR MENSAGENS, ASSOCIANDO-O À CONEXÃO (p)
                ObjectOutputStream sSerOut = new ObjectOutputStream(conexoes.get(id).getSocket().getOutputStream());
                sSerOut.writeObject(resposta); //ESCREVE NO PACOTE
                System.out.println(" -S- Enviando mensagem resposta...");
                sSerOut.flush(); //ENVIA O PACOTE

                //FINALIZA A CONEXÃO
                conexoes.get(id).getServSocket().close();
                conexoes.get(id).getSocket().close();
                System.out.println(" -S- Conexao finalizada...");

                if(msgIn.toString().equals(palavra))
                {
                    fecharConexoes();
                }
            }

            catch(Exception e)
            {
                try
                {
                    System.out.println(" -S- O seguinte problema ocorreu : \n" + e.toString());
                    conexoes.get(id).getServSocket().close();
                    conexoes.get(id).getSocket().close();
                }

                catch(Exception ex){}
            }
        }
    }
}