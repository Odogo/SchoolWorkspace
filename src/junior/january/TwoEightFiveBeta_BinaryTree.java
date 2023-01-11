package junior.january;

import junior.global.Commission;
import junior.global.binarytree.BinaryTree;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
/usr/lib/jvm/zulu-19-amd64/bin/java -javaagent:/snap/intellij-idea-community/405/lib/idea_rt.jar=39579:/snap/intellij-idea-community/405/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /media/[redacted]/Odogo/Workspace/School/SchoolWorkspace/bin junior.january.TwoEightFiveBeta_BinaryTree
{Id:138, Code: 17, Sales:6375.0, Com: 677.5}
{Id:192, Code: 8, Sales:8125.0, Com: 640.625}
{Id:118, Code: 8, Sales:7350.0, Com: 574.75}
{Id:125, Code: 5, Sales:6500.0, Com: 502.5}
{Id:264, Code: 17, Sales:4150.0, Com: 410.5}
{Id:235, Code: 5, Sales:5250.0, Com: 396.25}
{Id:218, Code: 5, Sales:5000.0, Com: 375.0}
{Id:103, Code: 5, Sales:4000.0, Com: 300.0}
{Id:203, Code: 8, Sales:3250.0, Com: 243.75}
{Id:101, Code: 17, Sales:2250.0, Com: 213.75}
{Id:291, Code: 17, Sales:750.0, Com: 71.25}
{Id:117, Code: 3, Sales:7350.0, Com: -2.0}

Process finished with exit code 0
 */
public class TwoEightFiveBeta_BinaryTree {

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

        BinaryTree<Commission> tree = null;
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");

                Commission commission = new Commission(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[2]));
                if(tree == null) tree = new BinaryTree<Commission>(commission);
                else tree.add(commission);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        tree.traverseTreeInfix();
    }

}
