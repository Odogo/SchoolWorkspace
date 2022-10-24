package junior.october.msoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/*
 * [Prompted user for file]
 * [File selected prob6_msoe.txt]
 .----------------.  .----------------.
| .--------------. || .--------------. 
| | ____    ____ | || |    _______   | 
| ||_   \  /   _|| || |   /  ___  |  | 
| |  |   \/   |  | || |  |  (__ \_|  | 
| |  | |\  /| |  | || |   '.___`-.   | 
| | _| |_\/_| |_ | || |  |`\____) |  | 
| ||_____||_____|| || |  |_______.'  | 
| |              | || |              | 
| '--------------' || '--------------' 
 '----------------'  '----------------'
 .----------------.  .----------------.
| .--------------. || .--------------. 
| |     ____     | || |  _________   | 
| |   .'    `.   | || | |_   ___  |  | 
| |  /  .--.  \  | || |   | |_  \_|  | 
| |  | |    | |  | || |   |  _|  _   | 
| |  \  `--'  /  | || |  _| |___/ |  | 
| |   `.____.'   | || | |_________|  | 
| |              | || |              | 
| '--------------' || '--------------' 
 '----------------'  '----------------'
 */
public class MSOEFive {
	
	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		String output = "";
		try (Scanner scanner = new Scanner(selection)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				char[] split = line.toCharArray();

				for(int i=0; i<split.length; i+=2) {
					if(i >= split.length || i+1 >= split.length) break;
					
					int amount = Integer.parseInt(split[i] + "");
					char use = split[i+1];
					for(int x=0; x<amount; x++) output += use;
				}
				
				output += "\n";
			}
		} catch (NoSuchElementException | NumberFormatException e) {
			System.err.println("Caught exception: Unexpected end of data file, program will continue running.");
			System.err.println("Given stack trace [CAN BE IGNORED]: ");
			e.printStackTrace();
		}
		
		System.out.println(output);
	}
}
