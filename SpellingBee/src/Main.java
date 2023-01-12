import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		DBThingy dbt = new DBThingy();
		String letters = "cloraju";
		dbt.printWords(letters);
	}
	
	public static void checkAdding() {
		DBThingy dbt = new DBThingy();
		String filename = "dictionary.txt";
		File f = new File(filename);
		String filename2 = "aaamissing.txt";
		File f2 = new File(filename2);
		FileWriter fw;
		int i = 0;
		int j = 0;
		try {
			fw = new FileWriter(f2);
			Scanner sc = new Scanner(f);
			while(sc.hasNext()) {
				j++;
				String word = sc.next();
				if (!dbt.contains(word) && word.length() > 3){
					fw.write(word + '\n');
					i++;
				}	
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		System.out.println(i);
		System.out.println(j);
	}
	
	public static void addStuff() {
		DBThingy dbt = new DBThingy();
		String filename = "dictionary.txt";
		File f = new File(filename);
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				dbt.addWord(sc.next());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
