package test;
import main.ClackClient;
    public class TestClackClient {
        public static void main(String[] args){
           ClackClient client = new ClackClient("Devon","Host",6969);
           ClackClient client2 = new ClackClient("Lark","Host2");
           ClackClient client3 = new ClackClient();
//
//            System.out.println(client.getUserName() + " " + client.getHostName() + " " + client.getPort() + " " + client.hashCode());
//            System.out.println(client2.getUserName() + " " + client2.getHostName() + " " + client2.getPort() + " " + client.hashCode());
//            System.out.println(client3.getUserName() + " " + client3.getHostName() + " " + client3.getPort() + " " + client.hashCode());
//            System.out.print('\n');
//            System.out.println("Is Client equal to Client? " + client.equals(client));
//            System.out.println("Is Client equal to Client2? " + client.equals(client2));
//            System.out.println("Is Client equal to Client3? " + client.equals(client3));
//            System.out.print('\n');
//            System.out.println(client.toString());
//            System.out.println(client2.toString());
//            System.out.println(client3.toString());

            client.start();
        }

    }



