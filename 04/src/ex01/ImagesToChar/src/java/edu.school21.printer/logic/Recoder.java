package edu.school21.printer.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Recoder {
    private char    black;
    private char    white;

    public Recoder(char black, char white)
    {
        this.black = black;
        this.white = white;
    }

    private int[] toArray()
    {
        try {
            FileInputStream fin = new FileInputStream("../target/resources/it.bmp");
            int ch;
            int i = 0;
            int j = 0;
            int[] arr = new int[32];
            while ((ch = fin.read()) != -1)
            {
                if (i > 60) {
                    if ((i - 60) % 4 == 2 || (i - 60) % 4 == 3)
                    {
                        arr[j] = ch;
                        j++;
                    }
                }
                i++;

            }
            return arr;

        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printByte(int ch)
    {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++)
        {
            arr[i] = (ch % 2);
            ch = ch /2;
        }
        for (int i = 7; i >= 0; i--)
        {
            if (arr[i] == 0)
                System.out.print(white);
            else
                System.out.print(black);
            System.out.print(" ");
        }
    }

    public void print(){
        int[] array = toArray();
        int [][] arr = new int[16][2];

        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j <arr[j].length ; j++) {
                arr[i][j] = array[i * arr[j].length + j];
            }
        }

        for(int i = 15; i >= 0; i--)
        {
            for (int j = 0; j < 2; j++)
            {
                printByte(arr[i][j]);
            }
            System.out.println("");
        }
    }
}
