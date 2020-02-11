package app;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App implements ActionListener {

    JFrame frame = new JFrame();
    JButton browse_but = new JButton("Browse");
    JButton save_but = new JButton("Save");
    JButton save_as_but = new JButton("Save As");
    JButton counter_but = new JButton("Count");
    JButton reset = new JButton("Reset");
    JTextArea area = new JTextArea(10, 30);
    JTextArea result_area = new JTextArea(15, 30);
    JScrollPane pane = new JScrollPane(area);
    JFileChooser chooser = new JFileChooser();
    String data = null;
    String path;

    // Constructor
    App() {
        frame.setSize(800, 600);
        // frame.setLocation(200, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(browse_but);
        frame.add(save_but);
        frame.add(save_as_but);
        frame.add(counter_but);
        frame.add(reset);
        frame.add(pane);
        frame.add(result_area);

        // Let's Make buttons Functional on which we add listener
        browse_but.addActionListener(this);
        save_but.addActionListener(this);
        save_as_but.addActionListener(this);
        counter_but.addActionListener(this);
        reset.addActionListener(this);


        //Make Frame Visible
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new App();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == browse_but) {
            int i = chooser.showOpenDialog(null);
            if (i == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getAbsolutePath();
                File file = new File(path);
                try {
                    Scanner reader = new Scanner(file);
                    while (reader.hasNext()) {
                        data = reader.nextLine();
                    }
                    area.setText(data);
                } catch (FileNotFoundException e1) {
                    
                    e1.printStackTrace();
                }
            } else if (i == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "File not Selected..");
            }
        }

        // Save Button
        if (e.getSource() == save_but) {
            chooser = new JFileChooser("E:");
            data = area.getText(); // get Text from textArea and put in to data
            path = chooser.getSelectedFile().getAbsolutePath(); // get Absolute Selected path
            // Always surround it with try catch block b/c it throw Expections
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(data);
                writer.close();  // Always close file
                JOptionPane.showMessageDialog(null,"Wrote Succussfully...");


            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error Occured...");
            }
         }



         if(e.getSource() == save_as_but){
            
            int j = chooser.showSaveDialog(null);
            if(j == JFileChooser.APPROVE_OPTION){
                data = area.getText();
                path = chooser.getSelectedFile().getAbsolutePath();

                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(data);
                    writer.close();

                    JOptionPane.showMessageDialog(null, "Wrote Succussfully..");


                } catch (IOException e1) {
                    System.out.println("An error Occured..");
                    e1.printStackTrace();
                }

            }
            else if(j == JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Canceal clicked..");
            }
         }

        else if(e.getSource() == counter_but){
            path = chooser.getSelectedFile().getAbsolutePath();
            File file = new File(path);
           
            try {

                BufferedReader fileReader = new BufferedReader(new FileReader(path));
                //create scanner class to read all file
                Scanner read = new Scanner(file); // it throws Exception

                char ch;
                int vowel = 0;
                int consonant = 0;
                int sentence = 1;
                int space = 0;
                int digits = 0;
                int aa = 0, uu = 0, ii = 0, ee = 0, oo = 0;
                String data;

                
                while(read.hasNext()){
                    data = read.next();
                    data = data.toLowerCase();

                    for(int i = 0; i < data.length(); i++){
                        ch = data.charAt(i);
                        if(ch=='a'|| ch =='e'|| ch=='i'|| ch=='u'|| ch=='o'){
                           
                            ++vowel;
                            }
                       else if(ch >= 'a' && ch <= 'z'){
                            ++consonant;
                        }
                       else if(ch=='.'  || ch=='!'){
                            ++sentence;
                        }
                        else if(ch >= 0 || ch <= 9){
                            ++digits;
                        }
                        else if(ch == ' '){
                            ++space;

                        }

                        switch(ch){
                            case 'a':
                            ++aa;  break;
                            case 'e':
                            ++ee; break;
                            case 'u':
                            ++uu;  break;
                            case 'o':
                            ++oo;  break;
                            case 'i': 
                            ++ii; break;
                            default:
                            
                             }
                     


                        
                        }

                        String count_result = "Total number of vowels is: " + vowel
                        + "\n Number of 'a'= " + aa 
                        + "\n Number of 'e'= " + ee
                        + "\n Number of 'u'= " + uu
                        + "\n Number of 'i'= " + ii
                        + "\n Number of 'o'= " + oo
                         + "\n Total number of Consonant is: " + consonant +
                         "\n Total number of sentences: " + sentence + 
                         "\n Total number of Digits: " + digits + 
                         "\n Total number of WhiteSpaces: " + space;

                        result_area.setText(count_result);
                      
                    }

                    
            

            }
             catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
         }

         if(e.getSource() == reset){
             area.setText("");
             result_area.setText("");
         }

    }
}