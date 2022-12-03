package main;

public class ClientSideServerListener implements Runnable{
    ClackClient client = new ClackClient();
    protected static final String DEFAULTKEY = "JHGJASKJ";
   public ClientSideServerListener(ClackClient client){
       this.client = client;
   }
   @Override
   public void run(){

               while (!client.getCloseConnection()) {

                   client.receiveData();
                   client.printData();
               }

   }


}
