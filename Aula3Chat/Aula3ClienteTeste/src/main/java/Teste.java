import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Teste {
    public static  void main(String[] args) {
        String nome = "";
        String msgp = "";

        nome = JOptionPane.showInputDialog("Bem vindo, ao Chat! Qual é o seu nome?");
        while (nome.equals("") || nome == null) {
            nome = JOptionPane.showInputDialog("O seu nome não pode ser nulo. Qual é o seu nome?");
        }
        try {
            while (msgp != "O") {
                msgp = JOptionPane.showInputDialog("Chat " + nome +
                        " entre com a msg ou entre com 0 para sair");
                if (msgp.equals("0")) {
                    System.out.println("Saiu do sistema");
                    System.exit(1);
                } else {
                    IChatAula objChat = (IChatAula) Naming.lookup("rmi://localhost:8282/chat");
                    Message msg = new Message(nome, msgp);
                    objChat.sendMessage(msg);
                    System.out.println(returnMessage(objChat.retrieveMessage()));
                }
            }
            System.out.println("Saiu do Programa");
            System.exit(1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String returnMessage(List<Message> lst){
        String valor = "";
        for(Message message : lst){
            valor += message.getUsuario() + ": " + message.getMessage() + "\n";
        }
        return valor;
    }
}
