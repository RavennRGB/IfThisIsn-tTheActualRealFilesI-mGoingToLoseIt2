package main;

import data.ClackData;
import data.ListUsersClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ClackServer {
    int port;

String userList;
    private static final int DEFAULT_PORT = 6969;
    protected static final String DEFAULTKEY = "JHGJASKJ";
    boolean closeConnection;
    public ListUsersClackData LUClackData;
//    public ClackData dataToReceiveFromClient;
//    public ClackData dataToSendToClient;
//    ObjectOutputStream outToClient;
//    ObjectInputStream inFromClient;
    ArrayList<ServerSideClientIO> serverSideClientIOList;


    public ClackServer(int port) {
        if (port < 1024)
            throw new IllegalArgumentException("Port Number must be greater than 1024.");
        else
            this.port = port;
        this.closeConnection = false;
        serverSideClientIOList = new ArrayList<ServerSideClientIO>();
LUClackData = new ListUsersClackData();
//        this.dataToReceiveFromClient = null;
//        this.dataToSendToClient = null;
//        this.outToClient = null;
//        this.inFromClient = null;



    }

    public ClackServer() {
        this(DEFAULT_PORT);
    }

   public void start() {
                try {
           ServerSocket sskt = new ServerSocket(port);
//            System.out.println("Waiting to accept client");
//            Socket clientSkt = sskt.accept();
//            System.out.println("accepted");
//            this.outToClient = new ObjectOutputStream(clientSkt.getOutputStream());
//            this.inFromClient = new ObjectInputStream(clientSkt.getInputStream());

           while (!this.closeConnection) {
               System.out.println("Waiting to accept client");
               Socket clientSkt = sskt.accept();
               System.out.println("accepted");
               ServerSideClientIO serverSideClientIO = new ServerSideClientIO(this, clientSkt);

               serverSideClientIOList.add(serverSideClientIO);
               //System.out.println(serverSideClientIO.UserList);
               Thread serverSideClientIOThread = new Thread(serverSideClientIO);
               serverSideClientIOThread.start();
//               remove(serverSideClientIO);

           }

           sskt.close();

//            this.outToClient.close();
//            this.inFromClient.close();
       } catch (IOException ioe) {
           System.err.println("IO Exception occurred start");
       }
   }

public synchronized void createUserList(){
        for(ServerSideClientIO i : serverSideClientIOList){
            userList = i.clientUsername + '\n';
            System.out.println(userList);
        }
}
    public synchronized void remove(ServerSideClientIO serverSideClientToRemove){
        serverSideClientIOList.remove(serverSideClientToRemove);
    }

    public synchronized void broadcast( ClackData dataToBroadcastToClients){
        for(ServerSideClientIO i : serverSideClientIOList){
            i.setDataToSendToClient((dataToBroadcastToClients));
            i.sendData();
            //i.dataToSendToClient((UserList));
        }
    }

//    public void receiveData() {
//        try {
//            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
//
//        } catch (IOException ioe) {
//            System.err.println("IO Exception");
//        } catch (ClassNotFoundException cnfe) {
//            System.err.println("class not found");
//        } catch (RuntimeException rte) {
//            System.err.println("runtime Exception");
//        }
//
//    }

//    public void sendData() {
//        try {
//
//            this.outToClient.writeObject(this.dataToSendToClient);
//
//        } catch (IOException ioe) {
//            System.err.println("IO Exception");
//        } catch (RuntimeException rte) {
//            System.err.println("runtime Exception");
//        }
//    }

    public int getPort() {
        return this.port;
    }


    public int hashCode() {
        return 17 * (1 + this.port);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClackServer)) {
            return false;
        }

        ClackServer otherClackServer = (ClackServer) other;
        return (this.port == otherClackServer.port && this.closeConnection == otherClackServer.closeConnection);
    }

    public String toString() {
        //return ("Port: " + this.port);
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n";
//                + "Data to receive from the client: " + this.dataToReceiveFromClient + "\n"
//                + "Data to send to the client: " + this.dataToSendToClient + "\n";
    }


    public static void main(String[] args) {
        try {
        ClackServer server;
        if (args.length == 0) {
            server = new ClackServer();
        } else {

                int port = Integer.parseInt(args[0]);
                server = new ClackServer(port);

        server.start();
    }
        } catch(NumberFormatException ime){System.err.println("Incorrect input");}
    }
}

