import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        Console console = System.console();
        String userName = console.readLine("\nEnter your name: ");
//        File file = new File("src/main/resources/db.csv");
//        File file = new File("C:\\Users\\ikech\\Desktop\\Decagon Weekly Task\\ChatServer\\src\\main\\resources\\db.csv");
        File file = new File("/Users/decagon/Desktop/db.csv");
        var usernames = new ArrayList<String[]>();

        while (true) {
            usernames = DbUtils.readCSVUsingBufferedReader(file);
            System.out.println(usernames);
            var result = checkUserName(usernames, userName);
            if (!result) break;
            userName = console.readLine("\nUserName taken enter another name: ");
        }

        DbUtils.writeDataLineByLine(file, userName);

        client.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            text = console.readLine("[" + client.getUserName() + "]: ");
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }

    private boolean checkUserName(ArrayList<String[]> usernames, String userName) {
        for (String[] strings : usernames) {
            var string = strings[0];
            System.out.println(string);
            System.out.println(userName);
            if (string.equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
