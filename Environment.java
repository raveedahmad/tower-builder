package towerbuilder2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import static java.awt.Font.SERIF;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Environment extends JPanel implements ActionListener{
    
    int[] x_blocks=new int[1000];
    int[] y_blocks=new int[1000];
    int[] cut=new int[1000];
    static boolean replay=false;
    public static final int width=505;
    public static final int height=650;
    static JFrame frame=new JFrame();;
    ImageIcon bg,fb;
    Button b1,b2;
    int background_x=0;
    int background_y=73;
    int x_mouse=-5;
    int app=-2;
    int y_mouse=-5;
    boolean right=true, gameOver=false,y_incr=true, appriciation, appriciation_e;
    int score;
    
    
    public void startEnvironment(){
        x_blocks [0]=135;
        y_blocks[0]=513;
        y_blocks[1]=y_blocks[0]-69;
        for (int i=1;i<1000;i++){
            x_blocks[i]+=10;
        }
        
        Color c1=new Color(1,147,188);
        Color c2=new Color(225,225,237);
        
        Font font=new Font(SERIF,BOLD,50);

        ImageIcon titleImage=new ImageIcon("C:\\Users\\Free User\\Desktop\\TowerBuilder2\\src\\towerbuilder2\\aa.jpg");
        
        
        frame.setBounds(430,30,width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(c1);
        frame.setTitle("Tower Builder");
        frame.setIconImage(titleImage.getImage());
        frame.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                x_mouse=e.getX();
                y_mouse=e.getY();
                }
        });
        frame.add(this);
    }
    public void paintComponent(Graphics g){
        bg=new ImageIcon("C:\\Users\\Free User\\Desktop\\TowerBuilder2\\src\\towerbuilder2\\Back.jpg");
        fb=new ImageIcon("C:\\Users\\Free User\\Desktop\\TowerBuilder2\\src\\towerbuilder2\\Base.png");
        ImageIcon bb=new ImageIcon("C:\\Users\\Free User\\Desktop\\TowerBuilder2\\src\\towerbuilder2\\BricksBlock.png");
        Color c1=new Color(1,147,188);
        Color c2=new Color(225,225,237);
        g.setColor(c1);
        g.fillRect(0, 0, 505, 650);
        g.setColor(c2);
        g.setFont(new Font(SERIF,BOLD,50));
        g.drawString("TOWER BUILDER",40,50);
        
        if (!gameOver){
            g.drawImage(bg.getImage(), background_x, background_y, null);
            g.drawImage(fb.getImage(), x_blocks[0], y_blocks[0], null);
            for (int n=1;n<1000;n++){
                if (x_blocks[n]!=0 && y_blocks[n]!=0)
                    g.drawImage(bb.getImage(), x_blocks[n], y_blocks[n],bb.getIconWidth()-cut[n],bb.getIconHeight(), null);
            }
            g.setColor(c2);
            g.setFont(new Font(SERIF,BOLD,20));
            g.drawString("Score: "+score, 10, 93);
            g.setColor(new Color(48,214,18));
            if (appriciation){
                g.setFont(new Font(SERIF,BOLD,50));
                g.drawString("Good", 204, 105);
                if(app>-1 && app<80){
                    app+=1;
                }else{
                    appriciation=false;
                }
                
            }
            if (appriciation_e){
                g.setFont(new Font(SERIF,BOLD,50));
                g.drawString("Exellent", 180, 105);
                if(app>-1 && app<80){
                    app+=1;
                }else{
                    appriciation_e=false;
                }
            }
        }
        if (gameOver){
            g.setColor(c1);
            g.fillRect(0, 63, 505, 650);
            g.setColor(c2);
            g.setFont(new Font(SERIF,BOLD,30));
            g.drawString("Score: "+(score-1), 10, 103);
            g.setFont(new Font(SERIF,BOLD,75));
            g.drawString("Game Over", 77, 235);
        }
        
    }
    public void run(){
        int x_incr=3;
        int sleepTime=17;
        replay=false;
        while(!gameOver){
            int a=0;
            if (mouse()){
                if (Math.abs(x_blocks[score+1]-x_blocks[score])>(234-cut[score+1])){
                    gameOver=true;
                }
                if (Math.abs(x_blocks[score+1]-x_blocks[score])<11 && Math.abs(x_blocks[score+1]-x_blocks[score])>4){
                    appriciation=true;
                    appriciation_e=false;
                    app=0;
                }
                if ((Math.abs(x_blocks[score+1]-x_blocks[score])<5)){
                    appriciation_e=true;
                    appriciation=false;
                    app=0;
                }
                if ((Math.abs(x_blocks[score+1]-x_blocks[score])>11) ){
                    a=Math.abs(x_blocks[score+1]-x_blocks[score]);
                    cut[score+1]+=a;
                }
                if (x_blocks[score+1]<x_blocks[score] && Math.abs(x_blocks[score+1]-x_blocks[score])>11){
                    x_blocks[score+1]=x_blocks[score];
                }
                
                
                cut[score+2]+=cut[score+1];
                int r=(int) (Math.random()*(3));
                if (r==0){
                    x_blocks[score+2]=x_blocks[score+1];
                }else if (r==1){
                    x_blocks[score+2]=width-(244-cut[score]);
                }
                
                
                y_blocks[score+2]=y_blocks[score+1]-69;
                if (score %5==0 && score>0 && sleepTime>12)
                    sleepTime-=1;

                score+=1;
                y_incr=true;
            }
        
            if (x_blocks[score+1]<width-(254-cut[score]) && right){
                x_blocks[score+1]+=x_incr;
            }
            else
                right=false;
            if(x_blocks[score+1]>10 && right==false){
                x_blocks[score+1]-=x_incr;
            }
            else 
                right=true;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
            }

            if (score>4 && score>0){
                if (y_incr){
                    y_incr=false;
                    if (score==5){
                        background_y+=120;
                    }
                    for (int i=0;i<1000;i++){
                        if (y_blocks[i]!=0)
                            y_blocks[i]+=69;
                    }
                }
            }if (!gameOver){
                repaint();
            }
        }
    }
    public boolean mouse(){
        if (x_mouse<505 && x_mouse>-2 && y_mouse>-2 && y_mouse<650){
            x_mouse=-5;
            y_mouse=-5;
            return true;}
        else 
            return false; 
    }
    public void gameOver(){
        
        b1=new Button("Replay");
        b2=new Button("QUIT");
        b1.setFont(new Font(SERIF,BOLD,28));
        b1.setBackground(new Color(255,230,191));
        b1.setBounds(200,300,100,50);
        b1.setForeground(new Color(210,180,248));
        b2.setFont(new Font(SERIF,BOLD,28));
        b2.setBackground(new Color(255,230,191));
        b2.setBounds(200,370,100,40);
        b2.setForeground(new Color(210,180,248));
        b1.addActionListener(this);
        b2.addActionListener(this);
        frame.add(b1);
        frame.add(b2);
        }
    public void actionPerformed(ActionEvent e){
        try{
            if (e.getSource()==b1){
            score=0;
            y_blocks=new int[1000];
            x_blocks [0]=135;
            y_blocks[0]=513;
            y_blocks[1]=y_blocks[0]-69;
            
            for (int i=1;i<1000;i++){
                x_blocks[i]=10;
            }
            cut=new int[1000];
            background_x=0;
            background_y=73;
            app=-2;
            right=true;
            gameOver=false;
            y_incr=false;
            y_mouse=-5;
            x_mouse=-5;
            frame.remove(b1);
            frame.remove(b2);
            replay=true;
            repaint();

        }else if (e.getSource()==b2)
            System.exit(0);
        }
        catch (Exception a){
            a.printStackTrace();
        }
    
    }
}

