package main;

import data.ClackData;
import data.ListUsersClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideClientIO implements Runnable {
    boolean closeConnection = false;
    ClackData dataToReceiveFromClient;
    protected static final String DEFAULTKEY = "JHGJASKJ";
    ClackData dataToSendToClient;
    ObjectInputStream inFromClient;
    ObjectOutputStream outToClient;
    ArrayList UserList;
    ClackServer server;
    Socket clientSocket;
    String clientUsername;

    ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;
        inFromClient = null;
        outToClient = null;
        UserList = null;
    }

    @Override
    public void run() {
        try {
            this.outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inFromClient = new ObjectInputStream(clientSocket.getInputStream());
           // receiveUserName();
//           String un = dataToReceiveFromClient.getUserName();
//            String newun = dataToReceiveFromClient.getUserName();
//           UserList.add(newun);
//            server.broadcast();

            while(!closeConnection){
                this.receiveData();
                if (dataToReceiveFromClient instanceof ListUsersClackData) {
                    this.server.broadcast(new MessageClackData("Server", this.server.LUClackData.getData(), DEFAULTKEY, ClackData.CONSTANT_DEFAULT_TYPE));
                    System.out.println(this.server.LUClackData.getData(DEFAULTKEY));
                } else {
                    this.server.LUClackData.addUser(dataToReceiveFromClient.getUserName());
                    System.out.println("User added");
                    this.server.broadcast(dataToReceiveFromClient);
                }
                //setDataToSendToClient(dataToReceiveFromClient);
                //sendData();
               // this.server.broadcast(this.dataToReceiveFromClient);
//                this.server.broadcast();
            }

        } catch (IOException ioe) {
            System.err.println("IOException occurred run");
        }
    }

public void receiveUserName() {
        try {
            clientUsername = (String) this.inFromClient.readObject();
        }catch (IOException ioe){
            System.err.println("IO Exception");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found");
        }
}
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
            System.out.println(dataToReceiveFromClient);
            if (!this.closeConnection) {
                System.out.println(dataToReceiveFromClient.getType());
                if(dataToReceiveFromClient.getData().equals("DONE")) {
                    this.closeConnection = true;
                    System.out.println("User List: " + this.server.LUClackData.getData());
                    this.server.LUClackData.delUser(dataToReceiveFromClient.getUserName());
                    server.remove(this);
                    System.out.println("got passed remove");
                //}else if(dataToReceiveFromClient.getData().equals("LISTUSERS")){
//    System.out.println("User List: " + this.server.LUClackData.getData());

                }
//                    this.closeConnection=true;
//                    server.remove(this);

                System.out.println("after");
               // System.out.println(dataToReceiveFromClient.getUserName());
            }


//

        } catch (IOException ioe) {

        } catch (ClassNotFoundException cnfe) {
            System.err.println("class not found");
        } catch (RuntimeException rte) {
            System.err.println("runtime Exception receive");
        }
    }
    public void sendData() {

        try {
            this.outToClient.writeObject(this.dataToSendToClient);

        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in sendData(): " + ice.getMessage());

        } catch (NotSerializableException nse) {
            System.err.println("NotSerializableException thrown in sendData(): " + nse.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in sendData(): " + ioe.getMessage());
        }
    }

//    public void sendData() {
//        System.out.println("before try");
//        try {
//            System.out.println("before");
//            this.outToClient.writeObject(this.dataToSendToClient);
//            System.out.println("after");
//        } catch (IOException ioe) {
//            System.err.println("IO Exception send");
//        } catch (RuntimeException rte) {
//            System.err.println("runtime Exception send");
//        }
//    }

    public void setDataToSendToClient(ClackData dataToSendToClient){
        this.dataToSendToClient = dataToSendToClient;
    }
}
