package junior.december;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Finished reading the datafile, continuing.
 * 
 * dne on sah gnidneps siH
 * dneirf taerg ym si nus ehT
 * dlog eht snwo taht eh tuB
 * dloh gnihton stekcop yM
 * lufituaeb dna dalg sgnihT
 * lla dna efil gninrom dooG
 */
public class NineZeroOneAlpha {

	public static void main(String[] args) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		
		File file = chooser.getSelectedFile();
		if(file == null) {
			System.err.println("Selected file does not exist.");
			return;
		} else if(!file.isFile()) {
			System.err.println("Selected file is not a readable file.");
			return;
		}
		
		LinkedList<String> list = new LinkedList<>();
		try(Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				list.add(scanner.nextLine());
			}
			
			System.out.println("Finished reading the datafile, continuing.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();
		for(int i=list.size()-1; i>=0; i--) {
			String line = list.get(i);
			for(int x=line.length()-1; x>=0; x--) builder.append(line.charAt(x));
			builder.append("\n");
		}

		System.out.println(builder.toString());
	}

}
