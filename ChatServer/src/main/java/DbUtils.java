import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbUtils {

    public static List<String[]> readCSVUsingScanner(File csvFile) {
        List<String[]> returnValue = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNextLine()) {
                String[] rowArr = scanner.nextLine().split(",");
                returnValue.add(rowArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList<String[]> readCSVUsingBufferedReader(File csvFile) {
        ArrayList<String[]> returnValue = new ArrayList<>();
        String line = "";

        try {
            FileReader filereader = new FileReader(csvFile);
            BufferedReader bufferedReader = new BufferedReader(filereader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowArr = line.split(",");
                returnValue.add(rowArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public static void writeDataLineByLine(File filePath, String userName) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(filePath, true))) {
            userName = userName + "," + "\n";
            output.write(userName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean  deleteUsernameFromDb(File filePath, String userName) {
        var list = readCSVUsingBufferedReader(filePath);
        for (String[] strings: list) {
            if (strings[0].equals(userName)) {
                strings[0] = "";
                return true;
            }
        }

        return false;
    }

}
