package junior.october.msoe2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/*
 * [Prompted user for file]
 * [File selected prob6_msoe.txt]
1 1.9-7-1.2 1.9-7-1.
1|1 1.9-5-1.1 2|1 1.9-5-1.1 
1|1 1|1 4_4 4_1 1|1 2|1 1|4 7_3 1|1 
1|1 2|1_3 1\2 1/3 1_2|1 2|1 1|3 1/2 3_2 1|2 1|1 
1|1 1|2 1|3 1\1/3 1|2 1|1 2|1 1|2 1|2 1(2_1 1\1_1|2 1|1 
1|1 1|2 1|1 1|1\2 1/1|1 1|2 1|1 2|1 1|3 1'1.3_1`1-1.3 1|1 
1|1 1|1 1_1|1 1|1_1\1/1_1|1 1|1_1 1|1 2|1 1|2 1|1`1\4_1)1 1|2 1|1 
1|1 2|5_2|5_2|1 2|1 1|2 1|7_1.1'2 1|1 
1|1 1|9 5 1|1 2|1 1|9 5 1|1 
1|1 1'9-5-1'1 2|1 1'9-5-1'1 
1 1'9-7-1'2 1'9-7-1'
1 1.9-7-1.2 1.9-7-1.
1|1 1.9-5-1.1 2|1 1.9-5-1.1 
1|1 1|5 4_5 1|1 2|1 1|2 9_3 1|1 
1|1 1|3 1.1'4 1`1.3 1|1 2|1 1|1 1|1_3 3_2 1|2 1|1 
1|1 1|2 1/2 1.2-1.2 1\2 1|1 2|1 1|3 1|1 1|1_2 1\1_1|2 1|1 
1|1 1|2 1|1 1|4 1|1 1|2 1|1 2|1 1|3 1|2 1_1|2 1_3 1|1 
1|1 1|2 1\2 1`2-1'2 1/2 1|1 2|1 1|2 1_1|1 1|3_1/1 1|2 1|1 
1|1 1|3 1`1.4_1.1'3 1|1 2|1 1|1 1|9_1|2 1|1 
1|1 1|9 5 1|1 2|1 1|9 5 1|1 
1|1 1'9-5-1'1 2|1 1'9-5-1'1 
1 1'9-7-1'2 1'9-7-1'
 */
public class MSOE2018Six {

	public static class CompressionData {
		private char symbol; private int amount;
		public CompressionData(char symbol, int amount) { this.symbol = symbol; this.amount = amount; }
		public char getSymbol() { return symbol; }
		public int getAmount() { return amount; }
	}
	
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

				List<CompressionData> datas = new ArrayList<>();
				Character current = null;
				int currentAmount = 0;
				for(int i=0; i<split.length; i++) {
					if((current != null && current != split[i]) || !(currentAmount < 9)) {
						datas.add(new CompressionData(current, currentAmount));
						currentAmount = 0;
						current = null;
					}
					
					current = split[i];
					currentAmount++;
					
					System.out.println(current);
				}
				
				for(CompressionData data : datas) {
					output += data.getAmount() + "" + data.getSymbol();
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
