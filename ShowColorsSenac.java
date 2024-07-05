 import javax.swing.JFrame;

public class ShowColorsSenac
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Meu painel");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      ColorSPanel colorSPanel = new ColorSPanel();
      frame.add(colorSPanel); 
      frame.setSize(260, 130);
      frame.setVisible(true);
   } 
} 