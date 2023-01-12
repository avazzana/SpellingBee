import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DBThingy {
	
	
	public void addWord(String word) {
		if (word.length() < 4) {
			return;
		}
		String builder = word.substring(0, 3);
		String filename = builder + "123.txt";
		File file = new File(filename);
		try {
			if (file.createNewFile()) {
				FileWriter fw = new FileWriter(file);
				fw.write(word);
				fw.close();
			}
			else {
				Scanner sc = new Scanner(file);
				File tempfile = new File("temp.txt");
				tempfile.createNewFile();
				FileWriter fw = new FileWriter(tempfile);
				boolean b = false;
				while (sc.hasNext()) {
					String w2 = sc.next();
					if (w2.equals(word)) {
						sc.close();
						fw.close();
						tempfile.delete();
						return;
					}
					else if (w2.compareTo(word) > 0 && !b) {
						b = true;
						fw.write(word + '\n');
					}
					fw.write(w2 + '\n');
				}
				if (!b) {
					fw.write(word + '\n');
				}
				sc.close();
				fw.close();
				file.delete();
				tempfile.renameTo(file);
				
			}
		} catch (IOException e) {
			System.out.println("an error occurred");
			e.printStackTrace();
		}
	}
	
	public boolean contains(String word) {
		if (word.length() < 4) {
			return false;
		}
		String filename = word.substring(0, 3) + "123.txt";
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
			while (sc.hasNext()) {
				String w1 = sc.next();
				if (w1.equals(word)) {
					sc.close();
					return true;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		
		return false;
	}
	
	public ArrayList<String> printWords(String letters){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> fileNames = new ArrayList<String>();
		File f = new File("C:\\Users\\vazzanam\\Desktop\\ICodeALot\\AudraciousnessPart6\\SpellingBee\\src\\");
		String[] names = f.list();
		for (String s: names) {
			String s1 = s.substring(0, 3);
			boolean b = true;
			for (int i = 0; i < 3; i++) {
				if (!letters.contains(s1.charAt(i) + "") || s.length() > 10) {
					b = false;
				}
			}
			if (b) {
				fileNames.add("C:\\Users\\vazzanam\\Desktop\\ICodeALot\\\\AudraciousnessPart6\\SpellingBee\\src\\" + s);
			}
		}
		for (String s: fileNames) {
			searchFile(list, s, letters);
		}
		for (String s: list) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println(list.size());
		System.out.println();
		System.out.println(findPanagram(list, letters));
		return list;
	}
	
	public String findPanagram(ArrayList<String> words, String letters) {
		for (String s: words) {
			boolean b = true;
			for (int i = 0; i < letters.length(); i++) {
				if (!s.contains(letters.charAt(i) + "")) {
					b = false;
				}
			}
			if (b) {
				return "panagram is: " + s;
			}
		}
		return "error: no panagram";
	}
	
	public void searchFile(ArrayList<String> list, String fileName, String letters) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNext()) {
				String s = sc.next();
				if (valid(s, letters)) {
					list.add(s);
				}
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean valid(String word, String letters) {
		if (!word.contains(letters.substring(0, 1))) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (!letters.contains(word.charAt(i) + "")) {
				return false;
			}
		}
		return true;
	}
	

}
