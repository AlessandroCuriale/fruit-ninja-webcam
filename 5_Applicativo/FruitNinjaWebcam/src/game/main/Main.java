
package game.main;

import game.component.PanelGame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
/**
 *
 * @author alessandro.curiale
 */

public class Main extends JFrame implements PanelGameListener{
    
    private javax.swing.JLabel label;
    private PanelGame panelGame;
    
    public Main(){
        init();
        this.panelGame.setListener(this);
    }
    private void init(){
        setTitle("FruitNinja");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panelGame = new PanelGame();
        add(panelGame);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e){
                panelGame.start();      
            }
        });
    }
//    public static void main(String[] args) {
//        Main main = new Main();
//        main.setVisible(true);
//    }

    @Override
    public void timeChanged() {
        label.setText("PROVA");
    }
}
