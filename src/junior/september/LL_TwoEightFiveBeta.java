package junior.september;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import junior.global.Commission;
import junior.global.linkedlist.Node;

/*
{Id:101, Code: 17, Sales:2250.0, Com: 213.75}
{Id:103, Code: 5, Sales:4000.0, Com: 300.0}
{Id:117, Code: 3, Sales:7350.0, Com: -2.0}
{Id:118, Code: 8, Sales:7350.0, Com: 574.75}
{Id:125, Code: 5, Sales:6500.0, Com: 502.5}
{Id:138, Code: 17, Sales:6375.0, Com: 677.5}
{Id:192, Code: 8, Sales:8125.0, Com: 640.625}
{Id:203, Code: 8, Sales:3250.0, Com: 243.75}
{Id:218, Code: 5, Sales:5000.0, Com: 375.0}
{Id:235, Code: 5, Sales:5250.0, Com: 396.25}
{Id:264, Code: 17, Sales:4150.0, Com: 410.5}
{Id:291, Code: 17, Sales:750.0, Com: 71.25}
Deleting no comms
Done, printing
{Id:101, Code: 17, Sales:2250.0, Com: 213.75}
{Id:103, Code: 5, Sales:4000.0, Com: 300.0}
{Id:118, Code: 8, Sales:7350.0, Com: 574.75}
{Id:125, Code: 5, Sales:6500.0, Com: 502.5}
{Id:138, Code: 17, Sales:6375.0, Com: 677.5}
{Id:192, Code: 8, Sales:8125.0, Com: 640.625}
{Id:203, Code: 8, Sales:3250.0, Com: 243.75}
{Id:218, Code: 5, Sales:5000.0, Com: 375.0}
{Id:235, Code: 5, Sales:5250.0, Com: 396.25}
{Id:264, Code: 17, Sales:4150.0, Com: 410.5}
{Id:291, Code: 17, Sales:750.0, Com: 71.25}
 */
public class LL_TwoEightFiveBeta {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");
		
		Node<Commission> root = null;
		Node<Commission> last = null;
		try (Scanner scanner = new Scanner(selection)) {

			// we do not read a line until in the loop.
			while (scanner.hasNextLine()) {
				// this resets the line to become the next line after everything is done
				// we do this at the start to make sure there is a line in the while loop.
				String line = scanner.nextLine();
				String[] split = line.split(" ");
				
				Commission com = new Commission(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[2]));
				Node<Commission> temp = new Node<Commission>(com);
				
				if(root == null) {
					root = temp;
					root.setPrev(null);
				} else if(last == null) {
					last = temp;
					last.setPrev(root);
					root.setNext(last);
				} else {
					last.setNext(temp);
					temp.setPrev(last);
					last = temp;
				}
			}
		} catch (NoSuchElementException | NumberFormatException e) {
			System.err.println("Caught exception: Unexpected end of data file, program will continue running.");
			System.err.println("Given stack trace [CAN BE IGNORED]: ");
			e.printStackTrace();
		}
		
		for(Node<Commission> node : root.fetchAllNodes()) System.out.println(node);
		System.out.println("Deleting no comms");
		
		for(Node<Commission> node : root.fetchAllNodes())
			if(node.fetchItem().fetchCommission() == -2) node.deleteNode();
		
		System.out.println("Done, printing");
		
		for(Node<Commission> node : root.fetchAllNodes()) System.out.println(node);
	}
}
