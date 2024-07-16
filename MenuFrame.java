import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuFrame extends JFrame 
{
   public MenuFrame() {
      super("Menu");
      
      JMenuBar bar = new JMenuBar();
      setJMenuBar(bar);

      JMenu fileMenu = new JMenu("Arquivo");
      fileMenu.setMnemonic('A');
      bar.add(fileMenu);

      JMenu editMenu = new JMenu("Editar");
      editMenu.setMnemonic('E');
      bar.add(editMenu);

   }
}
 