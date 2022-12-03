package test;
import main.ClackServer;


public class TestClackServer {
    public static void main(String[] args){
        ClackServer server = new ClackServer(6969);
        ClackServer server2 = new ClackServer();

        System.out.println(server.getPort() + " " + server.hashCode());
        System.out.println(server2.getPort() + " " + server2.hashCode());
        System.out.print('\n');
        System.out.println("Is Server equal to Server? " + server.equals(server));
        System.out.println("Is Server equal to Server2? " + server.equals(server2));
        System.out.print('\n');
        System.out.println(server.toString());
        System.out.println(server2.toString());
        server.start();


    }

}


