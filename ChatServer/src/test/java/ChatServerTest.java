import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {

    @Test
    void checkDbList() {
        File file = new File("/Users/decagon/Desktop/db.csv");
        var usernames = DbUtils.readCSVUsingBufferedReader(file);
        for (String[] strings : usernames) {
            var string = strings[0];
            System.out.println(string);
        }
        assertEquals(2, usernames.size());
    }
}