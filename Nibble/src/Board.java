/* sunt import-uri, e tot de ce ai nevoie, nu ar trebui sa schimbi, 
 * awt si swing sunt 2 frameworkuri care deseneaza
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/*
 * End of imports
 */


/*
 * Vreau sa-ti explic face2face niste chestii legate de OOP, dar vreau sa ai asta intai.
 * "extends" keyword : o clasa extinde alta clasa daca ii poate folosi toate campurile si functionalitatile
 *  si adauga altele noi, sau suprascrie functionalitati vechi.
 *  
 *  E ca si cum structura(clasa) A ar fi in interiorul clasei(structurii B)
 *  
 *  clasa B poate sa foloseasca tot ce are clasa A, sau poate sa-i suprascrie functii(cu @Override)
 *  
 *  "implements" keyword e folosit pentru interfete(interfetele nu contin cod, sunt doar niste entitati care descriu ce metode
 *  are o clasa si cum arata(dar nu ce face!!)
 */
public class Board extends JPanel implements ActionListener {

	/* detaliile tablitei de snake */
    private final int B_WIDTH = 300; /* latimea tablei */
    private final int B_HEIGHT = 300; /* inaltimea tablei */
    private final int DOT_SIZE = 10;  /* marmiea punctului */
    private final int ALL_DOTS = 900; /* numarul maxim de puncte astfel incat snake sa fie inca jucabil */
    private final int RAND_POS = 29;  /* pozitie random */
    private final int DELAY = 140;    /* delay-ul intre aparitiile unui mar, in msec */

    
    /* x si y sunt vectorii cu coordonatele  fiecarei bila din snake, practic, vector de coord */
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {
    	/* constructor */
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        /* trebuie sa mai incarci imaginile si sa initializezi jocul (hint initGame) */
	loadImages();	
	initGame();
    }

    private void loadImages() {
    	/* functia asta e scrisa de mine, nu face altceva decat sa-ti incarce imaginile */
        ImageIcon iid = new ImageIcon("dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("head.png");
        head = iih.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
    	/* aici verifici daca "capul" a mancat un mar, adica x[0] si y[0] au pozitia marului (hint, apple_x) */
    	/* va trebui sa incrementezi numarul de dots(cate elemente are snake) 
    	 * si sa apelezi locateApple() pentru a plasa un nou mar */
	if (x[0] == apple_x && y[0] ==apple_y)
	{
		dots++;
		locateApple();
	}	
    }

    private void move() {
    	/* directiile posibile pot fi leftDirection, right, up, down ,
    	 * trebuie sa calculezi cum se modifica coordonatele trenuletului de snake
    	 * in functie de fiecare mutare. (hint, intai calculezi sirul( nod[i] = nod[i-1]; si apoi
    	 * calculezi coordonatele capului
    	 */
	for (int z = dots ; z > 0; z--) {
            
		x[z] = x[z-1];
            	y[z] = y[z-1];
        }
	
	if(leftDirection) 
		x[0]-=DOT_SIZE;
	
	if(rightDirection) 
		x[0]+=DOT_SIZE;

	if(upDirection) 
		y[0]-=DOT_SIZE;

	if(downDirection) 
		y[0]+=DOT_SIZE;

    }

    private void checkCollision() {

    	/* verifici daca te-ai lovit de vreun perete sau de tine 
    	 * daca te-ai lovit faci inGame = false;
    	 * */
	if(x[0]==300 || y[0]==300 || x[0]==0 || y[0]==0)
		inGame = false;
	
	for (int z = 4 ; z < dots; z++) 
		if(x[0]==x[z] && y[0]==y[z])
		 inGame = false;
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
				/* ia sa le scrii tu pe restu, pt VK_RIGHT, UP si DOWN 
				* ppfffbt :p
				*/
	    if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

	    if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

	    if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}