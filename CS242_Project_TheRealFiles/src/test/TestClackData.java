package test;
import data.FileClackData;
import data.MessageClackData;
import data.ClackData;



public class TestClackData {
    public static void main (String args[]){

        ClackData clackdata = new MessageClackData("larks", "Enter File Name Here", 2);

MessageClackData data1 = new MessageClackData("larks", "Enter Message Here", 2);
        MessageClackData dataa = new MessageClackData("larks", "Enter Message Here", 2);
        MessageClackData datab = new MessageClackData("nope", "Enter Message Here", 2);
       FileClackData data2 = new FileClackData("larks", "Enter File Name Here", 2);
        FileClackData datay = new FileClackData("larks", "Enter File Name Here", 2);
        FileClackData dataz = new FileClackData("nope", "Enter File Name Here", 2);
//test printing
        System.out.println("Current username is " + data1.getUserName());
        System.out.println("Current file name is " + data2.getFileName());
        System.out.println("Current message is " + data1.getMessage());
        System.out.println("Current type is " + data1.getType());
        System.out.println("Set date is " + data1.getDate());
//test equals
        System.out.println(data1.equals(dataa));
        System.out.println(data1.equals(datab));
        System.out.println(data2.equals(datay));
        System.out.println(data2.equals(dataz));
//test to string
        System.out.println(data1.toString());
        System.out.println(data2.toString());
//hashcode
        System.out.println(data1.hashCode());
        System.out.println(data2.hashCode());
//ClackData
        System.out.println("\nMessage: Hello, World!");
        System.out.print("Encrypted: ");
        System.out.print(clackdata.encrypt("Hello, World!", "ABCDEF"));
        System.out.print("\nDecrypted: ");
        System.out.print(clackdata.decrypt(clackdata.encrypt("Hello, World!", "ABCDEF"), "ABCDEF"));

    }

}
