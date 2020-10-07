package chapter5;

/**
   A class that prepares a menu bar for controlling an
   OccurrenceCounter panel
   @see OccurrenceCounter.java
*/
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class OCMenuBar extends JMenuBar
{
   public OCMenuBar(final OccurrenceCounter ocPanel)
   {  super();
      // create the actions that can be performed
      Action newAction = new AbstractAction("New")
         {  public void actionPerformed(ActionEvent e)
            {  ocPanel.clear();
            }
         };
      Action openAction = new AbstractAction("Open")
         {  public void actionPerformed(ActionEvent e)
            {  JFileChooser chooser = new JFileChooser(new File("."));
               int status = chooser.showOpenDialog(ocPanel);
               if (status == JFileChooser.APPROVE_OPTION)
                  ocPanel.openFile(chooser.getSelectedFile());
            }
         };
      Action saveAction = new AbstractAction("Save")
         {  public void actionPerformed(ActionEvent e)
            {  JFileChooser chooser = new JFileChooser(new File("."));
               int status = chooser.showSaveDialog(ocPanel);
               if (status == JFileChooser.APPROVE_OPTION)
                  ocPanel.saveFile(chooser.getSelectedFile());
            }
         };
      Action exitAction = new AbstractAction("Exit")
         {  public void actionPerformed(ActionEvent e)
            {  System.exit(0);
            }
         };
      Action countAction = new AbstractAction("Count Words")
         {  public void actionPerformed(ActionEvent e)
            {  ocPanel.countWords();
            }
         };
      // add keyboard mnemonics for each action 
      newAction.putValue(Action.MNEMONIC_KEY, 
         new Integer(KeyEvent.VK_N));
      openAction.putValue(Action.MNEMONIC_KEY, 
         new Integer(KeyEvent.VK_O));
      saveAction.putValue(Action.MNEMONIC_KEY, 
         new Integer(KeyEvent.VK_S));
      exitAction.putValue(Action.MNEMONIC_KEY, 
         new Integer(KeyEvent.VK_X));
      countAction.putValue(Action.MNEMONIC_KEY, 
         new Integer(KeyEvent.VK_C));
      // add accelerators for some actions
      newAction.putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
      openAction.putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
      saveAction.putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
      // create the file menu
      JMenu fileMenu = new JMenu("File");
      fileMenu.setMnemonic(KeyEvent.VK_F);
      fileMenu.add(newAction);
      fileMenu.add(openAction);
      fileMenu.add(saveAction);
      fileMenu.addSeparator();
      fileMenu.add(exitAction);
      // create the view menu
      JMenu viewMenu = new JMenu("View");
      viewMenu.setMnemonic(KeyEvent.VK_V);
      viewMenu.add(countAction);
      // add both menus to this menu bar
      add(fileMenu);
      add(viewMenu);
   }
}
