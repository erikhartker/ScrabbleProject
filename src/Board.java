import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.nexagis.jawbone.Dictionary;

import javax.imageio.ImageIO;
import javax.swing.Box;
import java.util.ArrayList;

public class Board extends GUI{

    JFrame frame;

    //used to keep track of the player's total score for each turn

    public static int turnTotal;

    //2D array to store what letter was placed on what turn

    int[][] board = new int[boardSize][boardSize];

    //2D array to store the point value of each letter and where it's placed

    int[][] board2 = new int[boardSize][boardSize];

    //2D array to store each letter played

    String[][] board3 = new String[boardSize][boardSize];

    //counters for if the player lands on a double or triple word score

    int dW = 0;

    int tW = 0;

    //used to keep track of if the draw letter, exchange letter, or confirm word buttons have been pressed, respectively

    boolean drawLetterPress = false;


    boolean exchangeLetterPress = false;


    boolean confirmWordPressed = false;

    //to get the player's letter

    String PlayerLetterTxt;

    //to get the player's letters to save when changing turns

    String wordText;

    //array used to make an array list of all the letters so random ones can be given to the player

    String[] letterNames = { "a", "b", "c", "d", "e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","bl" };

    //array list that stores all the available letters in the bag

    ArrayList<String> letterBag = new ArrayList<String>();

    //this keeps track of whether the player has selected their letter for play

    boolean buttonPressed = false;

    //2D array of buttons which allows for each button to be kept track of

    JButton[][] boardButtons = new JButton[boardSize][boardSize];

    //array list used to store the point value of each letter played during the turn

    ArrayList<Integer> turnScore = new ArrayList<Integer>();

    //array list used to store the letter the user has played during the turn

    ArrayList<String> turnLetters = new ArrayList<String>();

    //array list which keeps track of the total scores for each turn

    public static ArrayList<Integer> turnTotals = new ArrayList<Integer>();

    //stores each player's letters on their letter bar

    String[] player1Letters = new String[7];
    String[] player2Letters = new String[7];
    String[] player3Letters = new String[7];
    String[] player4Letters = new String[7];

    // used to keep track of whose turn it is

    public static int turn = 1;

    //keeps track of any word that is played horizontally

    String turnWord = "";

    //keeps track of any word that is played vertically

    String turnWord2 = "";

    //used to store the coordinates of each letter placed on the board so words can be ordered correctly

    ArrayList<Integer> coords = new ArrayList<Integer>();

    ArrayList<Integer> coordsWord = new ArrayList<Integer>();



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Board window = new Board();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Board() {
        initialize();
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {


        makeLetterBag();

        JButton PlayerLetter = new JButton(DrawLetter());

        JButton PlayerLetter2 = new JButton(DrawLetter());

        JButton PlayerLetter3 = new JButton(DrawLetter());

        JButton PlayerLetter4 = new JButton(DrawLetter());

        JButton PlayerLetter5 = new JButton(DrawLetter());

        JButton PlayerLetter6 = new JButton(DrawLetter());

        JButton PlayerLetter7 = new JButton(DrawLetter());

        for (int i=0; i<7; i++) {
            player2Letters[i] = DrawLetter();
        }
        for (int i=0; i<7; i++) {
            player3Letters[i] = DrawLetter();
        }
        for (int i=0; i<7; i++) {
            player4Letters[i] = DrawLetter();
        }


        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JButton btnScoreboard = new JButton("Scoreboard");
        menuBar.add(btnScoreboard);
        btnScoreboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Scoreboard window = new Scoreboard();
                window.frame.setVisible(true);
            }
        });

        JButton btnTimer = new JButton("Timer");
        menuBar.add(btnTimer);
        btnTimer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TurnTimer window = new TurnTimer();
                window.frame.setVisible(true);
            }
        });

        JLabel playerTurn = new JLabel("Player 1");
        playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(playerTurn);



        frame.getContentPane().setLayout (new GridBagLayout ());
        GridBagConstraints a = new GridBagConstraints();

        GridBagConstraints c = new GridBagConstraints();

		/*these four variables are incremented and decremented so
		that the grid position of the button that will have DW on it is changed*/

        int w=0;
        int i=0;

        int h=boardSize-1;
        int g=0;

        int column=0;

        int n = 4;

        int tripLet = (boardSize-(((boardSize+1)/2)+2))%4;

        for (int gridx = 0; gridx < boardSize; gridx++) {
            for (int gridy = 0; gridy < boardSize; gridy++) {



                JButton ALetter = new JButton();


                c.fill = GridBagConstraints.NONE;
                c.gridx = gridx;
                c.gridy = gridy;
                c.ipady = 10;
                c.ipadx = -30;




                frame.getContentPane().add(ALetter, c);

                if (c.gridy == (boardSize/2)-2 || c.gridy == (boardSize/2)+2) {
                    if (c.gridx == (boardSize/2)-2 || c.gridx == (boardSize/2)+2) {
                        ALetter.setOpaque(true);
                        ALetter.setBackground(Color.cyan);
                        ALetter.setForeground(Color.gray);
                        ALetter.setText("TL");

                    }
                }


                if (c.gridx == (boardSize-1)-tripLet || c.gridx == 0+tripLet) {
                    if (c.gridy == (boardSize-1)-tripLet || c.gridy == 0+tripLet) {
                        ALetter.setOpaque(true);
                        ALetter.setText("TL");
                        System.out.println(tripLet);
                        ALetter.setBackground(Color.cyan);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx == (boardSize-1)-(tripLet+n) || c.gridx == 0+(tripLet+n)) {
                    if (c.gridy == (boardSize-1)-(tripLet) || c.gridy == 0+(tripLet)) {
                        ALetter.setOpaque(true);
                        ALetter.setText("TL");
                        System.out.println(tripLet);
                        ALetter.setBackground(Color.cyan);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx == (boardSize-1)-(tripLet) || c.gridx == 0+(tripLet)) {
                    if (c.gridy == (boardSize-1)-(tripLet+n) || c.gridy == 0+(tripLet+n)) {
                        ALetter.setOpaque(true);
                        ALetter.setText("TL");
                        System.out.println(tripLet);
                        ALetter.setBackground(Color.cyan);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx == (boardSize-1)-(tripLet) || c.gridx == 0+(tripLet)) {
                    if (c.gridy == (boardSize-1)-(tripLet+2*n) || c.gridy == 0+(tripLet+2*n)) {
                        if (2*n+1>((boardSize/2)-1)){

                        }
                        else {
                            ALetter.setOpaque(true);
                            ALetter.setText("TL");
                            System.out.println(tripLet);
                            ALetter.setBackground(Color.cyan);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }


                if (c.gridx == (boardSize-1)-(tripLet+2*n) || c.gridx == 0+(tripLet+2*n)) {
                    if (c.gridy == (boardSize-1)-(tripLet) || c.gridy == 0+(tripLet)) {
                        if (2*n+1>((boardSize/2)-1)){

                        }
                        else {
                            ALetter.setOpaque(true);
                            ALetter.setText("TL");
                            System.out.println(tripLet);
                            ALetter.setBackground(Color.cyan);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }

                if (c.gridx == (boardSize-1)-(tripLet+n) || c.gridx == 0+(tripLet+n)) {
                    if (c.gridy == (boardSize-1)-(tripLet+2*n) || c.gridy == 0+(tripLet+2*n)) {
                        if (2*n+1>((boardSize/2)-1)){

                        }
                        else {
                            ALetter.setOpaque(true);
                            ALetter.setText("TL");
                            System.out.println(tripLet);
                            ALetter.setBackground(Color.cyan);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }

                if (c.gridx == (boardSize-1)-(tripLet+2*n) || c.gridx == 0+(tripLet+2*n)) {
                    if (c.gridy == (boardSize-1)-(tripLet+n) || c.gridy == 0+(tripLet+n)) {
                        if (2*n+1>((boardSize/2)-1)){

                        }
                        else {
                            ALetter.setOpaque(true);
                            ALetter.setText("TL");
                            System.out.println(tripLet);
                            ALetter.setBackground(Color.cyan);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }

                if (c.gridx==w && c.gridy==i) {
                    if (i>(boardSize/2)+2 || i<(boardSize/2)-2) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DW");
                        ALetter.setBackground(Color.yellow);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx==g && c.gridy==h) {
                    if (h>(boardSize/2)+2 || h<(boardSize/2)-2) {
                        if (h==0 && g==boardSize-1) {

                        }
                        else {
                            ALetter.setOpaque(true);
                            ALetter.setText("DW");
                            ALetter.setBackground(Color.yellow);
                            ALetter.setForeground(Color.gray);
                        }

                    }
                }

                if (c.gridy == (boardSize/2)) {
                    if (c.gridx == (boardSize/2)-7 || c.gridx == (boardSize/2)+7) {
                        ALetter.setOpaque(true);
                        ALetter.setText("TW");
                        ALetter.setBackground(Color.pink);
                        ALetter.setForeground(Color.gray);
                        column = c.gridx;
                    }
                }

                if (c.gridy == (boardSize/2)+7 && c.gridx == (boardSize/2)+7) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize/2)-7 && c.gridx == (boardSize/2)+7) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize/2)-7 && c.gridx == (boardSize/2)-7) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize/2)+7 && c.gridx == (boardSize/2)-7) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (boardSize>15) {
                    if (c.gridy == (boardSize/2)+1 || c.gridy == (boardSize/2)-1) {
                        if (c.gridx == 0 || c.gridx == (boardSize-1)) {
                            ALetter.setOpaque(true);
                            ALetter.setText("TW");
                            column = c.gridx;
                            ALetter.setBackground(Color.pink);
                            ALetter.setForeground(Color.gray);
                        }
                    }

                    else if (c.gridx == (boardSize/2)+1 || c.gridx == (boardSize/2)-1) {
                        if (c.gridy == 0 || c.gridy == (boardSize-1)) {
                            ALetter.setOpaque(true);
                            ALetter.setText("TW");
                            column = c.gridx;
                            ALetter.setBackground(Color.pink);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }

                if (c.gridy == 0 && c.gridx == (boardSize-1)) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize-1) && c.gridx == (boardSize-1)) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == ((boardSize/2)-7) && c.gridx == (boardSize/2)) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == ((boardSize/2)+7) && c.gridx == (boardSize/2)) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }


                if (c.gridy == 0 && c.gridx == 0) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize-1) && c.gridx == 0) {
                    ALetter.setOpaque(true);
                    ALetter.setText("TW");
                    ALetter.setBackground(Color.pink);
                    ALetter.setForeground(Color.gray);
                }

                if (c.gridy == (boardSize/2)+7 || c.gridy == (boardSize/2)-7) {
                    if (c.gridx == (boardSize/2)+4 || c.gridx == (boardSize/2)-4) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridy == (boardSize/2)+4 || c.gridy == (boardSize/2)-4) {
                    if (c.gridx == (boardSize/2)+7 || c.gridx == (boardSize/2)-7) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridy == (boardSize/2)) {
                    if (c.gridx == (boardSize/2)+4 || c.gridx == (boardSize/2)-4) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx == (boardSize/2)) {
                    if (c.gridy == (boardSize/2)+4 || c.gridy == (boardSize/2)-4) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridx == (boardSize/2)+1 || c.gridx == (boardSize/2)-1) {
                    if (c.gridy == (boardSize/2)+5 || c.gridy == (boardSize/2)-5) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridy == (boardSize/2)+1 || c.gridy == (boardSize/2)-1) {
                    if (c.gridx == (boardSize/2)+5 || c.gridx == (boardSize/2)-5) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }

                if (c.gridy == (boardSize/2)+1 || c.gridy == (boardSize/2)-1) {
                    if (c.gridx == (boardSize/2)+1 || c.gridx == (boardSize/2)-1) {
                        ALetter.setOpaque(true);
                        ALetter.setText("DL");
                        ALetter.setBackground(Color.green);
                        ALetter.setForeground(Color.gray);
                    }
                }


                if (c.gridx == (boardSize/2) && c.gridy==(boardSize/2)) {
                    ALetter.setOpaque(true);
                    ALetter.setText("Start");
                    ALetter.setBackground(Color.PINK);
                    ALetter.setForeground(Color.gray);
                }

                if (boardSize>17) {
                    if (c.gridy == boardSize-1 || c.gridy == 0) {
                        if (c.gridx == (boardSize/2)+5 || c.gridx == (boardSize/2)-5) {
                            ALetter.setOpaque(true);
                            ALetter.setText("DL");
                            ALetter.setBackground(Color.green);
                            ALetter.setForeground(Color.gray);
                        }
                    }

                    if (c.gridy == (boardSize/2)+5 || c.gridy == (boardSize/2)-5) {
                        if (c.gridx == boardSize-1 || c.gridx == 0) {
                            ALetter.setOpaque(true);
                            ALetter.setText("DL");
                            ALetter.setBackground(Color.green);
                            ALetter.setForeground(Color.gray);
                        }
                    }
                }


                boardButtons[gridy][gridx] = ALetter;


                ALetter.addMouseListener(new MouseAdapter() {
                    @Override

                    public void mouseClicked(MouseEvent e) {


                        if (buttonPressed == true) {

                            int xPos = 0;
                            int yPos = 0;

                            for (int i = 0; i<boardSize; i++) {
                                for (int j = 0; j<boardSize; j++) {
                                    if (boardButtons[j][i] == ALetter) {
                                        xPos = i;
                                        yPos = j;
                                    }
                                }
                            }

                            System.out.println(xPos);
                            System.out.println(yPos);

                            if (exchangeLetterPress == false && drawLetterPress == false) {
                                if (((JButton) e.getSource()).getText() == "") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    StoreWords(xPos,yPos,turn);
                                    StoreWordValues(xPos,yPos);
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }
                                else if (((JButton) e.getSource()).getText() == "DL") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    ALetter.setForeground(Color.black);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    int baseNum = turnScore.get(turnScore.size()-1);
                                    int multipliedNum = baseNum*2;
                                    turnScore.remove(turnScore.size()-1);
                                    turnScore.add(multipliedNum);
                                    StoreWords(xPos,yPos,turn);
                                    board2[yPos][xPos] = baseNum;
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }

                                else if (((JButton) e.getSource()).getText() == "TL") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    ALetter.setForeground(Color.black);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    int baseNum = turnScore.get(turnScore.size()-1);
                                    int multipliedNum = baseNum*3;
                                    turnScore.remove(turnScore.size()-1);
                                    turnScore.add(multipliedNum);
                                    StoreWords(xPos,yPos,turn);
                                    board2[yPos][xPos] = baseNum;
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }

                                else if (((JButton) e.getSource()).getText() == "DW") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    ALetter.setForeground(Color.black);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    dW+=1;
                                    StoreWords(xPos,yPos,turn);
                                    StoreWordValues(xPos,yPos);
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }

                                else if (((JButton) e.getSource()).getText() == "TW") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    ALetter.setForeground(Color.black);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    tW+=1;
                                    StoreWords(xPos,yPos,turn);
                                    StoreWordValues(xPos,yPos);
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }

                                else if (((JButton) e.getSource()).getText() == "Start") {

                                    if (PlayerLetterTxt == "BL") {
                                        PlayerLetterTxt = JOptionPane.showInputDialog(null,"", "Input Letter", JOptionPane.WARNING_MESSAGE);
                                    }
                                    ALetter.setText(PlayerLetterTxt);
                                    ALetter.setForeground(Color.black);
                                    buttonPressed = false;
                                    TurnPoints(PlayerLetterTxt,turn);
                                    dW+=1;
                                    StoreWords(xPos,yPos,turn);
                                    StoreWordValues(xPos,yPos);
                                    CheckWord(xPos,yPos);
                                    storeCoords(xPos,yPos);
                                    board3[yPos][xPos] = PlayerLetterTxt;
                                    turnLetters.add(PlayerLetterTxt);
                                }
                            }
                            else {
                                if (PlayerLetter.isVisible()==false) {
                                    PlayerLetter.setVisible(true);
                                    buttonPressed = false;
                                }

                                else if (PlayerLetter2.isVisible()==false) {
                                    PlayerLetter2.setVisible(true);
                                    buttonPressed = false;
                                }

                                else if (PlayerLetter3.isVisible()==false) {
                                    PlayerLetter3.setVisible(true);

                                    buttonPressed = false;
                                }

                                else if (PlayerLetter4.isVisible()==false) {
                                    PlayerLetter4.setVisible(true);

                                    buttonPressed = false;
                                }

                                else if (PlayerLetter5.isVisible()==false) {
                                    PlayerLetter5.setVisible(true);

                                    buttonPressed = false;
                                }

                                else if (PlayerLetter6.isVisible()==false) {
                                    PlayerLetter6.setVisible(true);

                                    buttonPressed = false;
                                }

                                else if (PlayerLetter7.isVisible()==false) {
                                    PlayerLetter7.setVisible(true);

                                    buttonPressed = false;
                                }
                            }
                        }

                    }
                });
            }
            w++;
            i++;
            g++;
            h--;
        }

        //creates Draw Letter Button and places it on the board






        GridBagConstraints d = new GridBagConstraints();

        JButton ExchangeLetter = new JButton("Exchange Letter");
        d.fill = GridBagConstraints.HORIZONTAL;
        d.gridx = (boardSize+1)/3;
        d.gridy = boardSize+1;
        d.gridwidth = boardSize/3;
        frame.getContentPane().add(ExchangeLetter, d);



        GridBagConstraints e = new GridBagConstraints();

        JButton EndTurn = new JButton("End Turn");

        e.fill = GridBagConstraints.HORIZONTAL;
        e.gridx = (boardSize+1)*2/3;
        e.gridy = boardSize+1;
        e.gridwidth = boardSize/3;
        frame.getContentPane().add(EndTurn, e);

        EndTurn.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                if (confirmWordPressed == true) {
                    if (PlayerLetter.isVisible()==false) {

                        PlayerLetter.setVisible(true);
                        PlayerLetter.setText(DrawLetter());
                    }

                    if (PlayerLetter2.isVisible()==false) {
                        PlayerLetter2.setVisible(true);
                        PlayerLetter2.setText(DrawLetter());
                    }

                    if (PlayerLetter3.isVisible()==false) {
                        PlayerLetter3.setVisible(true);
                        PlayerLetter3.setText(DrawLetter());
                    }

                    if (PlayerLetter4.isVisible()==false) {
                        PlayerLetter4.setVisible(true);
                        PlayerLetter4.setText(DrawLetter());
                    }

                    if (PlayerLetter5.isVisible()==false) {
                        PlayerLetter5.setVisible(true);
                        PlayerLetter5.setText(DrawLetter());
                    }

                    if (PlayerLetter6.isVisible()==false) {
                        PlayerLetter6.setVisible(true);
                        PlayerLetter6.setText(DrawLetter());
                    }

                    if (PlayerLetter7.isVisible()==false) {
                        PlayerLetter7.setVisible(true);
                        PlayerLetter7.setText(DrawLetter());
                    }


                    if (turn % 4 == 1) {
                        wordText = PlayerLetter7.getText();
                        player1Letters[6] = wordText;

                        wordText = PlayerLetter6.getText();
                        player1Letters[5] = wordText;

                        wordText = PlayerLetter5.getText();
                        player1Letters[4] = wordText;

                        wordText = PlayerLetter4.getText();
                        player1Letters[3] = wordText;

                        wordText = PlayerLetter3.getText();
                        player1Letters[2] = wordText;

                        wordText = PlayerLetter2.getText();
                        player1Letters[1] = wordText;

                        wordText = PlayerLetter.getText();
                        player1Letters[0] = wordText;
                    }

                    else if (turn % 4 == 2) {
                        wordText = PlayerLetter7.getText();
                        player2Letters[6] = wordText;

                        wordText = PlayerLetter6.getText();
                        player2Letters[5] = wordText;

                        wordText = PlayerLetter5.getText();
                        player2Letters[4] = wordText;

                        wordText = PlayerLetter4.getText();
                        player2Letters[3] = wordText;

                        wordText = PlayerLetter3.getText();
                        player2Letters[2] = wordText;

                        wordText = PlayerLetter2.getText();
                        player2Letters[1] = wordText;

                        wordText = PlayerLetter.getText();
                        player2Letters[0] = wordText;
                    }

                    else if (turn % 4 == 3) {
                        wordText = PlayerLetter7.getText();
                        player3Letters[6] = wordText;

                        wordText = PlayerLetter6.getText();
                        player3Letters[5] = wordText;

                        wordText = PlayerLetter5.getText();
                        player3Letters[4] = wordText;

                        wordText = PlayerLetter4.getText();
                        player3Letters[3] = wordText;

                        wordText = PlayerLetter3.getText();
                        player3Letters[2] = wordText;

                        wordText = PlayerLetter2.getText();
                        player3Letters[1] = wordText;

                        wordText = PlayerLetter.getText();
                        player3Letters[0] = wordText;
                    }

                    else if (turn % 4 == 0) {
                        wordText = PlayerLetter7.getText();
                        player4Letters[6] = wordText;

                        wordText = PlayerLetter6.getText();
                        player4Letters[5] = wordText;

                        wordText = PlayerLetter5.getText();
                        player4Letters[4] = wordText;

                        wordText = PlayerLetter4.getText();
                        player4Letters[3] = wordText;

                        wordText = PlayerLetter3.getText();
                        player4Letters[2] = wordText;

                        wordText = PlayerLetter2.getText();
                        player4Letters[1] = wordText;

                        wordText = PlayerLetter.getText();
                        player4Letters[0] = wordText;
                    }


                    turn+=1;
                    exchangeLetterPress = false;
                    drawLetterPress = false;
                    turnTotal = getTurnScore();
                    if (dW>0) {
                        turnTotal = (int) (turnTotal*Math.pow(2, dW));
                    }
                    if (tW>0) {
                        turnTotal = (int) (turnTotal*Math.pow(3, tW));
                    }
                    resetTurnScore();
                    turnTotals.add(turnTotal);
                    System.out.println(turnTotals.size());
                    System.out.println(turnTotal);
                    turnTotal = 0;
                    dW = 0;
                    tW = 0;


                    if (turn % 4 == 1) {
                        playerTurn.setText("Player 1");
                        PlayerLetter.setText(player1Letters[0]);
                        PlayerLetter2.setText(player1Letters[1]);
                        PlayerLetter3.setText(player1Letters[2]);
                        PlayerLetter4.setText(player1Letters[3]);
                        PlayerLetter5.setText(player1Letters[4]);
                        PlayerLetter6.setText(player1Letters[5]);
                        PlayerLetter7.setText(player1Letters[6]);
                    }

                    else if (turn % 4 == 2) {
                        playerTurn.setText("Player 2");
                        PlayerLetter.setText(player2Letters[0]);
                        PlayerLetter2.setText(player2Letters[1]);
                        PlayerLetter3.setText(player2Letters[2]);
                        PlayerLetter4.setText(player2Letters[3]);
                        PlayerLetter5.setText(player2Letters[4]);
                        PlayerLetter6.setText(player2Letters[5]);
                        PlayerLetter7.setText(player2Letters[6]);
                    }

                    else if (turn % 4 == 3) {
                        playerTurn.setText("Player 3");
                        PlayerLetter.setText(player3Letters[0]);
                        PlayerLetter2.setText(player3Letters[1]);
                        PlayerLetter3.setText(player3Letters[2]);
                        PlayerLetter4.setText(player3Letters[3]);
                        PlayerLetter5.setText(player3Letters[4]);
                        PlayerLetter6.setText(player3Letters[5]);
                        PlayerLetter7.setText(player3Letters[6]);
                    }

                    else if (turn % 4 == 0) {
                        playerTurn.setText("Player 4");
                        PlayerLetter.setText(player4Letters[0]);
                        PlayerLetter2.setText(player4Letters[1]);
                        PlayerLetter3.setText(player4Letters[2]);
                        PlayerLetter4.setText(player4Letters[3]);
                        PlayerLetter5.setText(player4Letters[4]);
                        PlayerLetter6.setText(player4Letters[5]);
                        PlayerLetter7.setText(player4Letters[6]);
                    }
                    confirmWordPressed = false;
                }

                else {
                    JOptionPane.showMessageDialog(null,"Must Confirm Word", "", JOptionPane.WARNING_MESSAGE);
                }

            }

        });


        GridBagConstraints f = new GridBagConstraints();



        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = 0;
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter, f);
        PlayerLetter.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter.setVisible(false);
            }
        });





        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter2, f);
        PlayerLetter2.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter2.setVisible(false);
            }
        });



        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize*2/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter3, f);
        PlayerLetter3.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter3.setVisible(false);
            }
        });



        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize*3/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter4, f);
        PlayerLetter4.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter4.setVisible(false);
            }
        });




        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize*4/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter5, f);
        PlayerLetter5.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter5.setVisible(false);
            }
        });




        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize*5/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter6, f);
        PlayerLetter6.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter6.setVisible(false);
            }
        });




        f.fill = GridBagConstraints.HORIZONTAL;
        f.gridx = (boardSize*6/7);
        f.gridy = boardSize+2;
        f.gridwidth = 1;
        f.ipady = 10;
        f.ipadx = -120;

        f.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(PlayerLetter7, f);
        PlayerLetter7.addMouseListener(new MouseAdapter() {

            //this code makes the button of the player disappear after it is pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPressed = true;
                PlayerLetterTxt = ((JButton) e.getSource()).getText();
                PlayerLetter7.setVisible(false);
            }
        });


        JButton DrawLetter = new JButton("Draw Letter");

        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = boardSize+1;
        a.gridwidth = boardSize/3;
        frame.getContentPane().add(DrawLetter, a);


        DrawLetter.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                if (buttonPressed == false) {

                    if (PlayerLetter.isVisible()==false) {
                        PlayerLetter.setVisible(true);
                        PlayerLetter.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter2.isVisible()==false) {
                        PlayerLetter2.setVisible(true);
                        PlayerLetter2.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter3.isVisible()==false) {
                        PlayerLetter3.setVisible(true);
                        PlayerLetter3.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter4.isVisible()==false) {
                        PlayerLetter4.setVisible(true);
                        PlayerLetter4.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter5.isVisible()==false) {
                        PlayerLetter5.setVisible(true);
                        PlayerLetter5.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter6.isVisible()==false) {
                        PlayerLetter6.setVisible(true);
                        PlayerLetter6.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }

                    else if (PlayerLetter7.isVisible()==false) {
                        PlayerLetter7.setVisible(true);
                        PlayerLetter7.setText(DrawLetter());
                        buttonPressed = false;
                        drawLetterPress = true;
                    }
                }
            }

        });

        JButton btnConfirmWord = new JButton("Confirm Word");
        menuBar.add(btnConfirmWord);
        btnConfirmWord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean wordIsValid = true;
                confirmWordPressed = true;
                findWords();
                System.out.println(turnWord);
                System.out.println(turnWord2);
                for (int i = 0; i<boardSize; i++) {
                    for (int j = 0; j<boardSize; j++) {
                        if (board[i][j]==turn) {
                            if (board[i+1][j]==0 && board[i-1][j]==0 && board[i][j+1]==0 && board[i][j-1]==0){

                                if (PlayerLetter.isVisible()==false) {
                                    PlayerLetter.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter2.isVisible()==false) {
                                    PlayerLetter2.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter3.isVisible()==false) {
                                    PlayerLetter3.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter4.isVisible()==false) {
                                    PlayerLetter4.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter5.isVisible()==false) {
                                    PlayerLetter5.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter6.isVisible()==false) {
                                    PlayerLetter6.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }

                                if (PlayerLetter7.isVisible()==false) {
                                    PlayerLetter7.setVisible(true);
                                    PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                    turnLetters.remove(turnLetters.size()-1);
                                    buttonPressed = false;

                                }
                                for (int y = 0; y<boardSize; y++) {
                                    for (int z = 0; z<boardSize; z++) {
                                        if (board[y][z]==turn) {
                                            if (boardButtons[y][z].getBackground()==Color.cyan) {
                                                boardButtons[y][z].setText("TL");
                                                boardButtons[y][z].setForeground(Color.gray);
                                            }
                                            else if (boardButtons[y][z].getBackground()==Color.green) {
                                                boardButtons[y][z].setText("DL");
                                                boardButtons[y][z].setForeground(Color.gray);
                                            }
                                            else if (boardButtons[y][z].getBackground()==Color.pink) {
                                                boardButtons[y][z].setText("TW");
                                                boardButtons[y][z].setForeground(Color.gray);
                                            }
                                            else if (boardButtons[y][z].getBackground()==Color.yellow) {
                                                boardButtons[y][z].setText("DW");
                                                boardButtons[y][z].setForeground(Color.gray);
                                            }
                                            else if (boardButtons[y][z].getBackground()==Color.PINK) {
                                                boardButtons[y][z].setText("Start");
                                                boardButtons[y][z].setForeground(Color.gray);
                                            }
                                            else {
                                                boardButtons[y][z].setText("");
                                            }
                                        }
                                    }
                                }
                                JOptionPane.showMessageDialog(null,"Invalid Letter Placement", "", JOptionPane.WARNING_MESSAGE);
                                wordIsValid = false;
                                invalidWordDeleteX();
                                invalidWordDeleteY();
                                break;
                            }
                        }
                    }
                }
                if (wordIsValid == true) {
                    Main check = new Main();
                    Dictionary dict = check.createDictionary("./dict");
                    if (turnWord.length()>0) {
                        if (check.wordExists(dict, turnWord) != true) {
                            while (turnScore.size()>0) {
                                turnScore.remove(0);
                            }
                            JOptionPane.showMessageDialog(null,"Invalid Word", "", JOptionPane.WARNING_MESSAGE);

                            if (PlayerLetter.isVisible()==false) {
                                PlayerLetter.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter2.isVisible()==false) {
                                PlayerLetter2.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter3.isVisible()==false) {
                                PlayerLetter3.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter4.isVisible()==false) {
                                PlayerLetter4.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter5.isVisible()==false) {
                                PlayerLetter5.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter6.isVisible()==false) {
                                PlayerLetter6.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter7.isVisible()==false) {
                                PlayerLetter7.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }
                            for (int g = 0; g<boardSize; g++) {
                                for (int h = 0; h<boardSize; h++) {
                                    if (board[g][h]==turn) {
                                        if (boardButtons[g][h].getBackground()==Color.cyan) {
                                            boardButtons[g][h].setText("TL");
                                            boardButtons[g][h].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[g][h].getBackground()==Color.green) {
                                            boardButtons[g][h].setText("DL");
                                            boardButtons[g][h].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[g][h].getBackground()==Color.pink) {
                                            boardButtons[g][h].setText("TW");
                                            boardButtons[g][h].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[g][h].getBackground()==Color.yellow) {
                                            boardButtons[g][h].setText("DW");
                                            boardButtons[g][h].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[g][h].getBackground()==Color.PINK) {
                                            boardButtons[g][h].setText("Start");
                                            boardButtons[g][h].setForeground(Color.gray);
                                        }
                                        else {
                                            boardButtons[g][h].setText("");
                                        }
                                    }
                                }
                            }
                            invalidWordDeleteX();
                        }
                        if (check.wordExists(dict, turnWord) == true) {
                            JOptionPane.showMessageDialog(null,"Word Confirmed in Dictionary", "", JOptionPane.WARNING_MESSAGE);

                        }
                    }
                    Main check2 = new Main();
                    Dictionary dict2 = check.createDictionary("./dict");
                    if (turnWord2.length()>0) {
                        if (check2.wordExists(dict2, turnWord2) != true) {
                            JOptionPane.showMessageDialog(null,"Invalid Word", "", JOptionPane.WARNING_MESSAGE);
                            while (turnScore.size()>0) {
                                turnScore.remove(0);
                            }
                            if (PlayerLetter.isVisible()==false) {
                                PlayerLetter.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter2.isVisible()==false) {
                                PlayerLetter2.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter3.isVisible()==false) {
                                PlayerLetter3.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter4.isVisible()==false) {
                                PlayerLetter4.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter5.isVisible()==false) {
                                PlayerLetter5.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter6.isVisible()==false) {
                                PlayerLetter6.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }

                            if (PlayerLetter7.isVisible()==false) {
                                PlayerLetter7.setVisible(true);
                                PlayerLetter.setText(turnLetters.get(turnLetters.size()-1));
                                turnLetters.remove(turnLetters.size()-1);
                                buttonPressed = false;
                                drawLetterPress = true;
                            }
                            for (int q = 0; q<boardSize; q++) {
                                for (int r = 0; r<boardSize; r++) {
                                    if (board[q][r]==turn) {
                                        if (boardButtons[q][r].getBackground()==Color.cyan) {
                                            boardButtons[q][r].setText("TL");
                                            boardButtons[q][r].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[q][r].getBackground()==Color.green) {
                                            boardButtons[q][r].setText("DL");
                                            boardButtons[q][r].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[q][r].getBackground()==Color.pink) {
                                            boardButtons[q][r].setText("TW");
                                            boardButtons[q][r].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[q][r].getBackground()==Color.yellow) {
                                            boardButtons[q][r].setText("DW");
                                            boardButtons[q][r].setForeground(Color.gray);
                                        }
                                        else if (boardButtons[q][r].getBackground()==Color.PINK) {
                                            boardButtons[q][r].setText("Start");
                                            boardButtons[q][r].setForeground(Color.gray);
                                        }
                                        else {
                                            boardButtons[q][r].setText("");
                                        }
                                    }
                                }
                            }
                            invalidWordDeleteY();
                        }
                        if (check.wordExists(dict2, turnWord2) == true) {
                            JOptionPane.showMessageDialog(null,"Word Confirmed in Dictionary", "", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

        ExchangeLetter.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                if ((PlayerLetter.isVisible()==false && PlayerLetter2.isVisible()==false) || (PlayerLetter.isVisible()==false && PlayerLetter3.isVisible()==false) || (PlayerLetter.isVisible()==false && PlayerLetter4.isVisible()==false) || (PlayerLetter.isVisible()==false && PlayerLetter5.isVisible()==false) || (PlayerLetter.isVisible()==false && PlayerLetter6.isVisible()==false) || (PlayerLetter.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else if ((PlayerLetter2.isVisible()==false && PlayerLetter3.isVisible()==false) || (PlayerLetter2.isVisible()==false && PlayerLetter4.isVisible()==false) || (PlayerLetter2.isVisible()==false && PlayerLetter5.isVisible()==false) || (PlayerLetter2.isVisible()==false && PlayerLetter6.isVisible()==false) || (PlayerLetter2.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else if ((PlayerLetter3.isVisible()==false && PlayerLetter4.isVisible()==false) || (PlayerLetter3.isVisible()==false && PlayerLetter5.isVisible()==false) || (PlayerLetter3.isVisible()==false && PlayerLetter6.isVisible()==false) || (PlayerLetter3.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else if ((PlayerLetter4.isVisible()==false && PlayerLetter5.isVisible()==false) || (PlayerLetter4.isVisible()==false && PlayerLetter6.isVisible()==false) || (PlayerLetter4.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else if ((PlayerLetter5.isVisible()==false && PlayerLetter6.isVisible()==false) || (PlayerLetter5.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else if ((PlayerLetter6.isVisible()==false && PlayerLetter7.isVisible()==false)) {
                    buttonPressed = false;
                    exchangeLetterPress = true;
                }

                else {
                    if (buttonPressed == true) {

                        if (PlayerLetter.isVisible()==false) {
                            PlayerLetter.setVisible(true);
                            PlayerLetter.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter2.isVisible()==false) {
                            PlayerLetter2.setVisible(true);
                            PlayerLetter2.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter3.isVisible()==false) {
                            PlayerLetter3.setVisible(true);
                            PlayerLetter3.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter4.isVisible()==false) {
                            PlayerLetter4.setVisible(true);
                            PlayerLetter4.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter5.isVisible()==false) {
                            PlayerLetter5.setVisible(true);
                            PlayerLetter5.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter6.isVisible()==false) {
                            PlayerLetter6.setVisible(true);
                            PlayerLetter6.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }

                        else if (PlayerLetter7.isVisible()==false) {
                            PlayerLetter7.setVisible(true);
                            PlayerLetter7.setText(DrawLetter());
                            buttonPressed = false;
                            exchangeLetterPress = true;
                        }
                    }
                }

            }

        });

    }

    public void StoreWords(int x, int y, int turn) {

        board[y][x] = turn;

    }

    public void StoreWordValues (int x, int y) {

        board2[y][x] = turnScore.get(turnScore.size()-1);

    }

    public void CheckWord(int x, int y) {


        if (x+1<boardSize) {
            if (board[y][x+1]!=turn) {
                int i = x+1;
                int z = 0;
                while (i<=boardSize-1 && (board[y][i]!=0 && board[y][i]!=turn)) {
                    storeCoords(i,y);
                    turnScore.add(board2[y][i]);

                    for (int j = 0; j<coords.size()-2; j=j+2) {
                        if (i == coords.get(j)) {
                            if (y == coords.get(j+1)) {
                                coords.remove(coords.size()-2);
                                coords.remove(coords.size()-1);
                                turnScore.remove(turnScore.size()-1);
                            }
                        }
                    }

                    z++;
                    i++;
                    if (i==boardSize) {
                        break;
                    }
                }
                if (z>1 && (board[y+1][x]!=0 || board[y-1][x]!=0)) {
                    storeCoords(x,y);
                    turnScore.add(board2[y][x]);
                }
            }
        }

        if (x-1>=0) {
            if (board[y][x-1]!=turn) {
                int i = x-1;
                int z = 0;
                while (i>=0 && (board[y][i]!=0 && board[y][i]!=turn)) {
                    storeCoords(i,y);
                    turnScore.add(board2[y][i]);

                    for (int j = 0; j<coords.size()-2; j=j+2) {
                        if (i == coords.get(j)) {
                            if (y == coords.get(j+1)) {
                                coords.remove(coords.size()-2);
                                coords.remove(coords.size()-1);
                                turnScore.remove(turnScore.size()-1);
                            }
                        }
                    }


                    z++;
                    i--;
                    if (i==-1) {
                        break;
                    }
                }
                if (z>1 && (board[y+1][x]!=0 || board[y-1][x]!=0)) {
                    storeCoords(x,y);
                    turnScore.add(board2[y][x]);
                }
            }
        }

        if (y-1>=0) {
            if (board[y-1][x]!=turn) {
                int i = y-1;
                int z = 0;
                while (i>=0 && (board[i][x]!=0 && board[i][x]!=turn)) {
                    storeCoords(x,i);
                    turnScore.add(board2[i][x]);

                    for (int j = 0; j<coords.size()-2; j=j+2) {
                        if (x == coords.get(j)) {
                            if (i == coords.get(j+1)) {
                                coords.remove(coords.size()-2);
                                coords.remove(coords.size()-1);
                                turnScore.remove(turnScore.size()-1);
                            }
                        }
                    }


                    z++;
                    i--;
                    if (i==-1) {
                        break;
                    }
                }
                if (z>1 && (board[y][x+1]!=0 || board[y+1][x-1]!=0)) {
                    storeCoords(x,y);
                    turnScore.add(board2[y][x]);
                }
            }
        }

        if (y+1<boardSize) {
            if (board[y+1][x]!=turn) {
                int i = y+1;
                int z = 0;
                while (i<=boardSize-1 && (board[i][x]!=0 && board[i][x]!=turn)) {
                    storeCoords(x,i);
                    turnScore.add(board2[i][x]);

                    for (int j = 0; j<coords.size()-2; j=j+2) {
                        if (x == coords.get(j)) {
                            if (i == coords.get(j+1)) {
                                coords.remove(coords.size()-2);
                                coords.remove(coords.size()-1);
                                turnScore.remove(turnScore.size()-1);
                            }
                        }
                    }


                    z++;
                    i++;
                    if (i==boardSize) {
                        break;
                    }
                }
                if (z>1 && (board[y][x+1]!=0 || board[y+1][x-1]!=0)) {
                    storeCoords(x,y);
                    turnScore.add(board2[y][x]);
                }
            }
        }
    }




    public void TurnPoints(String LetterText, int turnNum) {

        if (LetterText=="A") {
            turnScore.add(letterValues[0]);
            System.out.println(letterValues[0]);
        }

        else if (LetterText=="B") {
            turnScore.add(letterValues[1]);
        }

        else if (LetterText=="C") {
            turnScore.add(letterValues[2]);
        }

        else if (LetterText=="D") {
            turnScore.add(letterValues[3]);
        }

        else if (LetterText=="E") {
            turnScore.add(letterValues[4]);
        }

        else if (LetterText=="F") {
            turnScore.add(letterValues[5]);
        }

        else if (LetterText=="G") {
            turnScore.add(letterValues[6]);
        }

        else if (LetterText=="H") {
            turnScore.add(letterValues[7]);
        }

        else if (LetterText=="I") {
            turnScore.add(letterValues[8]);
        }

        else if (LetterText=="J") {
            turnScore.add(letterValues[9]);
        }

        else if (LetterText=="K") {
            turnScore.add(letterValues[10]);
        }

        else if (LetterText=="L") {
            turnScore.add(letterValues[11]);
        }

        else if (LetterText=="M") {
            turnScore.add(letterValues[12]);
        }

        else if (LetterText=="N") {
            turnScore.add(letterValues[13]);
        }

        else if (LetterText=="O") {
            turnScore.add(letterValues[14]);
        }

        else if (LetterText=="P") {
            turnScore.add(letterValues[15]);
        }

        else if (LetterText=="Q") {
            turnScore.add(letterValues[16]);
        }

        else if (LetterText=="R") {
            turnScore.add(letterValues[17]);
        }

        else if (LetterText=="S") {
            turnScore.add(letterValues[18]);
        }

        else if (LetterText=="T") {
            turnScore.add(letterValues[19]);
        }

        else if (LetterText=="U") {
            turnScore.add(letterValues[20]);
        }

        else if (LetterText=="V") {
            turnScore.add(letterValues[21]);
        }

        else if (LetterText=="W") {
            turnScore.add(letterValues[22]);
        }

        else if (LetterText=="X") {
            turnScore.add(letterValues[23]);
        }

        else if (LetterText=="Y") {
            turnScore.add(letterValues[24]);
        }

        else if (LetterText=="Z") {
            turnScore.add(letterValues[25]);
        }


    }


    private void makeLetterBag() {

        for (int i=0; i<letterAmounts[0]; i++) {
            letterBag.add("A");
        }

        for (int i=0; i<letterAmounts[1]; i++) {
            letterBag.add("B");
        }

        for (int i=0; i<letterAmounts[2]; i++) {
            letterBag.add("C");
        }

        for (int i=0; i<letterAmounts[3]; i++) {
            letterBag.add("D");
        }

        for (int i=0; i<letterAmounts[4]; i++) {
            letterBag.add("E");
        }

        for (int i=0; i<letterAmounts[5]; i++) {
            letterBag.add("F");
        }

        for (int i=0; i<letterAmounts[6]; i++) {
            letterBag.add("G");
        }

        for (int i=0; i<letterAmounts[7]; i++) {
            letterBag.add("H");
        }

        for (int i=0; i<letterAmounts[8]; i++) {
            letterBag.add("I");
        }

        for (int i=0; i<letterAmounts[9]; i++) {
            letterBag.add("J");
        }

        for (int i=0; i<letterAmounts[10]; i++) {
            letterBag.add("K");
        }

        for (int i=0; i<letterAmounts[11]; i++) {
            letterBag.add("L");
        }

        for (int i=0; i<letterAmounts[12]; i++) {
            letterBag.add("M");
        }

        for (int i=0; i<letterAmounts[13]; i++) {
            letterBag.add("N");
        }

        for (int i=0; i<letterAmounts[14]; i++) {
            letterBag.add("O");
        }

        for (int i=0; i<letterAmounts[15]; i++) {
            letterBag.add("P");
        }

        for (int i=0; i<letterAmounts[16]; i++) {
            letterBag.add("Q");
        }

        for (int i=0; i<letterAmounts[17]; i++) {
            letterBag.add("R");
        }

        for (int i=0; i<letterAmounts[18]; i++) {
            letterBag.add("S");
        }

        for (int i=0; i<letterAmounts[19]; i++) {
            letterBag.add("T");
        }

        for (int i=0; i<letterAmounts[20]; i++) {
            letterBag.add("U");
        }

        for (int i=0; i<letterAmounts[21]; i++) {
            letterBag.add("V");
        }

        for (int i=0; i<letterAmounts[22]; i++) {
            letterBag.add("W");
        }

        for (int i=0; i<letterAmounts[23]; i++) {
            letterBag.add("X");
        }

        for (int i=0; i<letterAmounts[24]; i++) {
            letterBag.add("Y");
        }

        for (int i=0; i<letterAmounts[25]; i++) {
            letterBag.add("Z");
        }

        for (int i=0; i<letterAmounts[26]; i++) {
            letterBag.add("BL");
        }
    }

    private String DrawLetter() {
        double pickerNumber = Math.random();

        int max = letterBag.size() - 1;

        double doubleLetterIndex = max*pickerNumber;

        int letterIndex = (int) doubleLetterIndex;

        String letter = letterBag.get(letterIndex);

        letterBag.remove(letterIndex);

        return letter;

    }



    private int getTurnScore() {
        int numTurnScore = 0;
        for (int i=0; i<=turnScore.size()-1; i++) {
            numTurnScore = numTurnScore + turnScore.get(i);
        }
        return numTurnScore;
    }

    private void resetTurnScore() {
        for (int i = turnScore.size()-1; i>=0; i--) {
            turnScore.remove(i);
        }
    }

    private int convertGridX(int x) {
        int boardNum = boardSize;
        boardNum = boardNum - 15;
        x = (x - 382);
        int z = (45*(boardNum/2));
        x = x - z;
        x = x/45;
        return x;
    }

    private int convertGridY(int y) {
        int boardNum = boardSize;
        boardNum = boardNum - 15;
        y = (y - 108);
        int z = (39*(boardNum/2));
        y = y - z;
        y = y/39;
        return y;
    }

    private void storeCoords(int x, int y) {
        coords.add(x);
        coords.add(y);
    }

    private void invalidWordDeleteX() {
        ArrayList<Integer> wordX = new ArrayList<Integer>();
        for (int i = 0; i<coordsWord.size(); i=i+2) {
            if (i!=0 && coordsWord.get(i)!=coordsWord.get(0)) {
                wordX.add(coordsWord.get(i));
                wordX.add(coordsWord.get(i+1));
            }
            if (i==0 && coordsWord.get(i)!=coordsWord.get(i+2)){
                wordX.add(coordsWord.get(i));
                wordX.add(coordsWord.get(i+1));
            }
        }
        for (int i =0; i<wordX.size(); i=i+2) {
            board3[wordX.get(i+1)][wordX.get(i)]="";
            board2[wordX.get(i+1)][wordX.get(i)]=0;
            board[wordX.get(i+1)][wordX.get(i)]=0;
        }
    }

    private void invalidWordDeleteY() {
        ArrayList<Integer> wordY = new ArrayList<Integer>();
        for (int i = 0; i<coordsWord.size(); i=i+2) {
            if (i!=0 && coordsWord.get(i+1)!=coordsWord.get(1)) {
                wordY.add(coordsWord.get(i));
                wordY.add(coordsWord.get(i+1));
            }
            if (i==0 && coordsWord.get(i+1)!=coordsWord.get(i+3)){
                wordY.add(coordsWord.get(i));
                wordY.add(coordsWord.get(i+1));
            }
        }
        for (int i =0; i<wordY.size(); i=i+2) {
            board3[wordY.get(i+1)][wordY.get(i)]="";
            board2[wordY.get(i+1)][wordY.get(i)]=0;
            board[wordY.get(i+1)][wordY.get(i)]=0;
        }
    }

    private void findWords() {

        ArrayList<Integer> wordX = new ArrayList<Integer>();
        ArrayList<Integer> wordY = new ArrayList<Integer>();

        ArrayList<String> wordRealX = new ArrayList<String>();
        ArrayList<String> wordRealY = new ArrayList<String>();


        for (int i = 0; i<coords.size(); i=i+2) {
            if (i!=0 && coords.get(i)!=coords.get(0)) {
                wordX.add(coords.get(i));
                wordX.add(coords.get(i+1));
            }
            if (i==0 && coords.get(i)!=coords.get(i+2)){
                wordX.add(coords.get(i));
                wordX.add(coords.get(i+1));
            }
        }

        for (int i = 0; i<coords.size(); i=i+2) {
            if (i!=0 && coords.get(i+1)!=coords.get(1)) {
                wordY.add(coords.get(i));
                wordY.add(coords.get(i+1));
            }
            if (i==0 && coords.get(i+1)!=coords.get(i+3)){
                wordY.add(coords.get(i));
                wordY.add(coords.get(i+1));
            }
        }

        for (int i = 0; i<wordX.size(); i=i+2) {
            for (int j = i+2; j<wordX.size(); j=j+2) {
                if (wordX.get(i)>wordX.get(j)) {

                    wordX.add(i, wordX.get(j));
                    wordX.add(j+1, wordX.get(i+1));
                    wordX.remove(j+2);
                    wordX.remove(i+1);

                    wordX.add(i+1, wordX.get(j+1));
                    wordX.add(j+2, wordX.get(i+2));
                    wordX.remove(j+3);
                    wordX.remove(i+2);
                }
            }
        }

        for (int i = 0; i<wordY.size(); i=i+2) {
            for (int j = i+2; j<wordY.size(); j=j+2) {
                if (wordY.get(i+1)>wordY.get(j+1)) {
                    wordY.add(i, wordY.get(j));
                    wordY.add(j+1, wordY.get(i+1));
                    wordY.remove(j+2);
                    wordY.remove(i+1);

                    wordY.add(i+1, wordY.get(j+1));
                    wordY.add(j+2, wordY.get(i+2));
                    wordY.remove(j+3);
                    wordY.remove(i+2);
                }
            }
        }

        for (int i = 0; i+1<wordX.size(); i=i+2) {
            wordRealX.add(board3[wordX.get(i+1)][wordX.get(i)]);
        }

        for (int i = 0; i+1<wordY.size(); i=i+2) {
            wordRealY.add(board3[wordY.get(i+1)][wordY.get(i)]);
        }

        StringBuffer sb = new StringBuffer();
        for (String s : wordRealX) {
            sb.append(s);
        }

        turnWord = sb.toString();

        StringBuffer sb1 = new StringBuffer();
        for (String s : wordRealY) {
            sb1.append(s);
        }

        turnWord2 = sb1.toString();


        while (wordX.size()>0) {
            wordX.remove(0);
        }

        while (wordX.size()>0) {
            wordY.remove(0);
        }

        while (wordX.size()>0) {
            wordRealX.remove(0);
        }

        while (wordX.size()>0) {
            wordRealY.remove(0);
        }

        for (int i = 0; i<coords.size(); i++) {
            coordsWord.add(i);
        }

        while (coords.size()>0) {
            coords.remove(0);
        }


    }

}
