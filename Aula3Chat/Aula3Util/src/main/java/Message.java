import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {
    private  static final long seriaVersionUID = - 272336051271966964L;

    private String user;
    private  String message;

    private static List<Message> lstMessage = new ArrayList<Message>();

    public Message(String user, String message){
        this.user = user;
        this.message = message;
    }

    public String getUsuario(){
        return  user;
    }

    public void setUsuario(){
        this.user = user;
    }
    public String getMessage(){
        return message;
    }

    public void setMessage(){
        this.message = message;
    }

    public static List<Message> getLstMessage(){
        return lstMessage;
    }

    public static void setLstMessage(Message msg){
        lstMessage.add(msg);
    }
}
