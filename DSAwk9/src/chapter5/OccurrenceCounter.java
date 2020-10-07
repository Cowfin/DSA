package chapter5;

/**
   A class which demonstrates how a hash map can be used to count the
   number of occurrences of a word in an editable document
*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.StringTokenizer;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OccurrenceCounter extends JPanel
{
   private JEditorPane displayArea; // editable text component 

   public OccurrenceCounter()
   {  super();
      displayArea = new JEditorPane();
      displayArea.setPreferredSize(new Dimension(600,450));
      add(new JScrollPane(displayArea));
   }
   
   // clears the display in displayArea
   public void clear()
   {  displayArea.setText("");
   }

   // open the specified text file and display in displayArea
   public void openFile(File file)
   {  try
      {  BufferedReader br = new BufferedReader(new FileReader(file));
         if (file.getName().toLowerCase().endsWith(".html"))
            displayArea.setContentType("text/html");
         else
            displayArea.setContentType("text/plain");
         displayArea.read(br, null);
      }
      catch (IOException event)
      {  JOptionPane.showMessageDialog(this, event.getMessage(),
            "Error Opening File", JOptionPane.ERROR_MESSAGE);
      }
   }

   // save current text in displayArea to the specified text file
   public void saveFile(File file)
   {  try
      {  PrintWriter pw = new PrintWriter(
            new BufferedWriter(new FileWriter(file)));
         String text = displayArea.getText();
         pw.print(text);
         pw.close();
      }
      catch (IOException event)
      {  JOptionPane.showMessageDialog(this, event.getMessage(),
            "Error Saving File", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   // display the occurrences of each word in the displayPanel
   public void countWords()
   {  // first count the occurrences of each word using a hash map
      Map<String,Integer> occurrenceMap
         = new HashMap<String,Integer>();
      Integer one = new Integer(1); // frequently used count
      StringTokenizer tokenizer
         = new StringTokenizer(displayArea.getText(), " ;,.\n\t()[]");
      while (tokenizer.hasMoreTokens())
      {  String word = tokenizer.nextToken().trim().toLowerCase();
         Integer frequency = occurrenceMap.get(word);
         if (frequency==null)
            frequency = one;
         else
            frequency = new Integer(frequency.intValue()+1);
         occurrenceMap.put(word, frequency);
      }
      // now transfer the map to a tree map so it gets sorted
      TreeMap<String,Integer> sortedMap 
         = new TreeMap<String,Integer>(occurrenceMap);
      // Prepare the results in a panel with two columns
      JPanel resultsPanel = new JPanel(new GridLayout(0,2));
      Iterator<Map.Entry<String,Integer>> iterator 
         = sortedMap.entrySet().iterator();
      while (iterator.hasNext())
      {  Map.Entry<String,Integer> pair = iterator.next();
         resultsPanel.add(new JLabel(pair.getKey()));
         resultsPanel.add(new JLabel(""+pair.getValue()));
      }
      // put the results in a frame
      JFrame resultsFrame = new JFrame("Summary");
      resultsFrame.getContentPane().add(new JScrollPane(resultsPanel));
      resultsFrame.setSize(new Dimension(400,300)); 
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = resultsFrame.getSize();
      resultsFrame.setLocation(
         (screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      resultsFrame.setVisible(true);
   }

   public static void main(String[] args)
   {  JFrame frame = new JFrame("Occurrence Counter");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      OccurrenceCounter panel = new OccurrenceCounter();
      frame.getContentPane().add(panel);
      frame.setJMenuBar(new OCMenuBar(panel));
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
   }
}
