import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CreateMap {
    private HashMap<String, String> map;

    public CreateMap(){
        map = new HashMap<String, String>();
        char[] mass = new char[65536];
        int i = 0;
        try {
            FileInputStream fin = new FileInputStream("signatures.txt");
            int ch;
            while ((ch = fin.read()) != -1){
                mass[i] = (char)ch;
                i++;
            }
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] newmass = new char[i];
        for (int j = 0; j < i; j++)
        {
            newmass[j] = mass[j];
        }

        makeMap(new String(newmass));
    }

    public HashMap<String,String> getMap(){
        return map;
    }

    private void makeMap(String mass)
    {
        String[] a = mass.split("\n");
        for (int i = 0; i < a.length; i++)
        {
            String[] split_spaces = a[i].split(" ");
            if (split_spaces.length <= 1)
                errorConfig();
            char[] bitmass = new char[split_spaces.length - 1];
            for (int j = 1; j < split_spaces.length; j++)
            {
                int n = Integer.valueOf(split_spaces[j], 16);
                bitmass[j - 1] = (char)n;
            }
            map.put(split_spaces[0].split(",")[0], new String(bitmass));
        }
    }
    private void errorConfig()
    {
        System.err.println("Error config file");
        System.exit(-1);
    }

    public void outTo(List<String> list)
    {
        try {
            FileOutputStream fout = new FileOutputStream("result.txt");
            for (String s : list) {
                s += "\n";
                fout.write(s.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
