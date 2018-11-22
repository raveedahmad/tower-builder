package towerbuilder2;

import javax.swing.JPanel;

public class TowerBuilder2 extends JPanel{ 

    

    public static void main(String[] args) {
        Environment t=new Environment();
        t.startEnvironment();
        t.run();
        while (true){
            t.gameOver();
            while (true){
                if (t.replay){
                    t.run();
                    break;
                }
            }
        }
    }
}