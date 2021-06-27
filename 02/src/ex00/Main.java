
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    List<String> list = new ArrayList<>();

        CreateMap create = new CreateMap();
        Map<String,String> map = create.getMap();
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("|Key: " + entry.getKey() + " Value: "
                    + entry.getValue() + "|");
        }
        Scanner scanner = new Scanner(System.in);
        String folder;
        while (!(folder = scanner.nextLine()).equals("42"))
        {
            String type;
            if ((type = foundHeader(folder, map)) != null) {
                System.out.println("PROCESSED");
                list.add(type);
            }
            else {
                System.out.println("UNDEFINED");
            }
        }
        create.outTo(list);

    }


    public static String foundHeader(String folder, Map<String,String> map)
    {
        try {
            FileInputStream fin = new FileInputStream(folder);
            String data = "";
            int ch;
            int i = 0;
            while ((ch = fin.read()) != -1 && i < 128)
            {
                data += (char)ch;
                i++;
            }
            fin.close();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (data.indexOf(value) == 0)
                    return key;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            return null;
        } catch (IOException e) {
            System.out.println("Reed error");
            e.printStackTrace();
        }

        return null;
    }
}
