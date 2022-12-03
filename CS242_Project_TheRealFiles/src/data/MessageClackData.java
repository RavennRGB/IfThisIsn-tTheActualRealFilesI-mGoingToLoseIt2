package data;

import javax.xml.crypto.Data;

public class MessageClackData extends ClackData{
    private String message;

    public final int CONSTANT_LOGOUT = 0;
    public final int CONSTANT_SENDMESSAGE =2;
    public MessageClackData(String userName, String message, int type){
        super(userName, type);
        this.message = message;
    }
    public MessageClackData(String userName, String message, String key, int type){
        super(userName, type);
        this.message = encrypt(message, key);
    }





    public MessageClackData() {
        super(ClackData.CONSTANT_SENDMESSAGE);
        this.message = "";
    }




    public String getMessage(){
        return this.message;
    }
    public String getData(){
        return this.message;
    };
    public String getData(String key){
        return decrypt(this.message, key);
    };


    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageClackData)) {
            return false;
        }

        MessageClackData otherMessage = (MessageClackData) other;
        return this.userName == otherMessage.userName &&
                this.message == otherMessage.message &&
                this.type == otherMessage.type;
    }

    public int hashCode(){
        int result = 5;
        result = 17 * result + this.userName.hashCode();
        result = 17 * result + this.message.hashCode();
        result = 17 * result + this.type;
        return result;

    }

    public String toString() {// return this.message;
        return "This instance of MessageClackData has the following properties:\n"
                + "Username: " + this.userName + "\n"
                + "Type: " + this.type + "\n"
                + "Date: " + this.date.toString() + "\n"
                + "Message: " + this.message + "\n";
    }// }


}


