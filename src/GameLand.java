//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.sql.SQLOutput;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /** STEP 1: Declare your object and give it a name **/
    public Hero astro;
    public Hero ob1; // old poppy
    public Hero ob2; // old lily
    public Hero ob3; // old rose
    public Hero ob4;
    public Hero ob5;
    public Hero ob6;
    /** STEP 2: Declare an image for your object **/
    public Image pastel;
    public Image astroPic;
    public Image ob1Pic;
    public Image ob2Pic;
    public Image ob3Pic;
    public Image ob4Pic;
    public Image ob5Pic;
    public Image ob6Pic;
    public boolean ob1IsIntersectingob2;
    public boolean ob1IsIntersectingob3;
    public boolean ob1IsIntersectingob4;
    public boolean ob1IsIntersectingob5;
    public boolean ob1IsIntersectingob6;
    public boolean ob2IsIntersectingob3;
    public boolean ob2IsIntersectingob4;
    public boolean ob2IsIntersectingob5;
    public boolean ob2IsIntersectingob6;
    public boolean ob3IsIntersectingob4;
    public boolean ob3IsIntersectingob5;
    public boolean ob3IsIntersectingob6;
    public boolean ob4IsIntersectingob5;
    public boolean ob4IsIntersectingob6;
    public boolean ob5IsIntersectingob6;
    int winner;


    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {

        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /** STEP 3: Construct a specific Hero object **/
//        astro = new Hero(400, 500, 1, 2);
//        astro.printInfo();
        ob1 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 2, 5, 1);
        ob2 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 3, 4, 2);
        ob3 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 2, 3, 3 );
        ob4 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 4, 7, 1);
        ob5 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 1, 5, 2);
        ob6 = new Hero((int)(Math.random() * 901), (int)(Math.random() * 601), 4, 3, 3);
        /** STEP 4: load in the image for your object **/
//        astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
        pastel = Toolkit.getDefaultToolkit().getImage("pastel.jpeg");
        ob1Pic = Toolkit.getDefaultToolkit().getImage("rock.png");
        ob2Pic = Toolkit.getDefaultToolkit().getImage("paper.png");
        ob3Pic = Toolkit.getDefaultToolkit().getImage("scissors.png");
        ob4Pic = Toolkit.getDefaultToolkit().getImage("rock.png");
        ob5Pic = Toolkit.getDefaultToolkit().getImage("paper.png");
        ob6Pic = Toolkit.getDefaultToolkit().getImage("scissors.png");
    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); // checks for all intersections
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of your objects below:
        // This is technically not the actual object! It's just a picture
        g.drawImage(pastel, 0,0, 1000, 700, null);

//        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
//        g.drawImage(ob1Pic, ob1.xpos, ob1.ypos, ob1.width, ob1.height, null);
//        g.drawImage(ob2Pic, ob2.xpos, ob2.ypos, ob2.width, ob2.height, null);
//        g.drawImage(ob3Pic, ob3.xpos, ob3.ypos, ob3.width, ob3.height, null);
//        g.drawImage(ob4Pic, ob4.xpos, ob4.ypos, ob4.width, ob4.height, null);
//        g.drawImage(ob5Pic, ob5.xpos, ob5.ypos, ob5.width, ob5.height, null);
//        g.drawImage(ob6Pic, ob6.xpos, ob6.ypos, ob6.width, ob6.height, null);
        imageDecision(ob1);
        imageDecision(ob2);
        imageDecision(ob3);
        imageDecision(ob4);
        imageDecision(ob5);
        imageDecision(ob6);

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void imageDecision(Hero object){
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        if (object.identity == 1){
            Image temp = Toolkit.getDefaultToolkit().getImage("rock.png");
            g.drawImage(temp, object.xpos, object.ypos, object.width, object.height, null);
        }
        else if (object.identity == 2){
            Image temp = Toolkit.getDefaultToolkit().getImage("paper.png");
            g.drawImage(temp, object.xpos, object.ypos, object.width, object.height, null);
        }
        else {
            Image temp = Toolkit.getDefaultToolkit().getImage("scissors.png");
            g.drawImage(temp, object.xpos, object.ypos, object.width, object.height, null);
        }
    }

    public void moveThings() {
        //call the move() method code from your object class
//        astro.bouncingMove();
        ob1.bouncingMove();
        ob2.wrappingMove();
        ob3.bouncingMove();
        ob4.bouncingMove();
        ob5.wrappingMove();
        ob6.bouncingMove();
    }

    public void collisions(){
        // makes them bounce off of each other
//            ob1.dx = -1 * ob1.dx;
//            ob1.dy = -1 * ob1.dy;
//            ob3.dx = -1 * ob3.dx;
//            ob3.dy = -1 * ob3.dy;
        // Stops moving the object and puts it far out of the screen to make it "disappear"
//            ob3.isAlive = false;
//            ob3.dy = 0;
//            ob3.dx = 0;
//            ob3.xpos = 2000;
        // Makes an object get bigger
//        if (ob2.rec.intersects(ob1.rec) && (!ob2IsIntersectingob1)){
//            ob2IsIntersectingob1 = true;
//            System.out.println("Ouch");
//            ob1.width++;
//            ob1.height++;
//        }
//        if (!(ob2.rec.intersects(ob1.rec))){
//            ob2IsIntersectingob1 = false;
//        }
        if (ob1.rec.intersects(ob2.rec) && (!ob1IsIntersectingob2)){
            ob1IsIntersectingob2 = true;
            winner = faceOff(ob1, ob2);
            if (winner == 1){
                ob2.identity = ob1.identity;
            }
            else if (winner == 2){
                ob1.identity = ob2.identity;
            }
        }
        if (!(ob1.rec.intersects(ob2.rec))){
            ob1IsIntersectingob2 = false;
        }

        if (ob1.rec.intersects(ob3.rec) && (!ob1IsIntersectingob3)){
            ob1IsIntersectingob3 = true;
            winner = faceOff(ob1, ob3);
            if (winner == 1){
                ob3.identity = ob1.identity;
            }
            else if (winner == 2){
                ob1.identity = ob3.identity;
            }
        }
        if (!(ob1.rec.intersects(ob3.rec))){
            ob1IsIntersectingob3 = false;
        }

        if (ob1.rec.intersects(ob4.rec) && (!ob1IsIntersectingob4)){
            ob1IsIntersectingob4 = true;
            winner = faceOff(ob1, ob4);
            if (winner == 1){
                ob4.identity = ob1.identity;
            }
            else if (winner == 2){
                ob1.identity = ob4.identity;
            }
        }
        if (!(ob1.rec.intersects(ob4.rec))){
            ob1IsIntersectingob4 = false;
        }

        if (ob1.rec.intersects(ob2.rec) && (!ob1IsIntersectingob5)){
            ob1IsIntersectingob5 = true;
            winner = faceOff(ob1, ob5);
            if (winner == 1){
                ob5.identity = ob1.identity;
            }
            else if (winner == 2){
                ob1.identity = ob5.identity;
            }
        }
        if (!(ob1.rec.intersects(ob5.rec))){
            ob1IsIntersectingob5 = false;
        }

        if (ob1.rec.intersects(ob6.rec) && (!ob1IsIntersectingob6)){
            ob1IsIntersectingob6 = true;
            winner = faceOff(ob1, ob6);
            if (winner == 1){
                ob6.identity = ob1.identity;
            }
            else if (winner == 2){
                ob1.identity = ob6.identity;
            }
        }
        if (!(ob1.rec.intersects(ob6.rec))){
            ob1IsIntersectingob6 = false;
        }

        if (ob2.rec.intersects(ob3.rec) && (!ob2IsIntersectingob3)){
            ob2IsIntersectingob3 = true;
            winner = faceOff(ob2, ob3);
            if (winner == 1){
                ob3.identity = ob2.identity;
            }
            else if (winner == 2){
                ob2.identity = ob3.identity;
            }
        }
        if (!(ob2.rec.intersects(ob3.rec))){
            ob2IsIntersectingob3 = false;
        }

        if (ob2.rec.intersects(ob4.rec) && (!ob2IsIntersectingob4)){
            ob2IsIntersectingob4 = true;
            winner = faceOff(ob2, ob4);
            if (winner == 1){
                ob4.identity = ob2.identity;
            }
            else if (winner == 2){
                ob2.identity = ob4.identity;
            }
        }
        if (!(ob2.rec.intersects(ob4.rec))){
            ob2IsIntersectingob4 = false;
        }

        if (ob2.rec.intersects(ob5.rec) && (!ob2IsIntersectingob5)){
            ob2IsIntersectingob5 = true;
            winner = faceOff(ob2, ob5);
            if (winner == 1){
                ob5.identity = ob2.identity;
            }
            else if (winner == 2){
                ob2.identity = ob5.identity;
            }
        }
        if (!(ob2.rec.intersects(ob5.rec))){
            ob2IsIntersectingob5 = false;
        }

        if (ob2.rec.intersects(ob6.rec) && (!ob2IsIntersectingob6)){
            ob2IsIntersectingob6 = true;
            winner = faceOff(ob2, ob6);
            if (winner == 1){
                ob6.identity = ob2.identity;
            }
            else if (winner == 2){
                ob2.identity = ob6.identity;
            }
        }
        if (!(ob2.rec.intersects(ob6.rec))){
            ob2IsIntersectingob6 = false;
        }

        if (ob3.rec.intersects(ob4.rec) && (!ob3IsIntersectingob4)){
            ob3IsIntersectingob4 = true;
            winner = faceOff(ob3, ob4);
            if (winner == 1){
                ob4.identity = ob3.identity;
            }
            else if (winner == 2){
                ob3.identity = ob4.identity;
            }
        }
        if (!(ob3.rec.intersects(ob4.rec))){
            ob3IsIntersectingob4 = false;
        }

        if (ob3.rec.intersects(ob5.rec) && (!ob3IsIntersectingob5)){
            ob3IsIntersectingob5 = true;
            winner = faceOff(ob3, ob5);
            if (winner == 1){
                ob5.identity = ob3.identity;
            }
            else if (winner == 2){
                ob3.identity = ob5.identity;
            }
        }
        if (!(ob3.rec.intersects(ob5.rec))){
            ob3IsIntersectingob5 = false;
        }

        if (ob3.rec.intersects(ob6.rec) && (!ob3IsIntersectingob6)){
            ob3IsIntersectingob6 = true;
            winner = faceOff(ob3, ob6);
            if (winner == 1){
                ob6.identity = ob3.identity;
            }
            else if (winner == 2){
                ob3.identity = ob6.identity;
            }
        }
        if (!(ob3.rec.intersects(ob6.rec))){
            ob3IsIntersectingob6 = false;
        }

        if (ob4.rec.intersects(ob5.rec) && (!ob4IsIntersectingob5)){
            ob4IsIntersectingob5 = true;
            winner = faceOff(ob4, ob5);
            if (winner == 1){
                ob5.identity = ob4.identity;
            }
            else if (winner == 2){
                ob4.identity = ob5.identity;
            }
        }
        if (!(ob4.rec.intersects(ob5.rec))){
            ob4IsIntersectingob5 = false;
        }

        if (ob4.rec.intersects(ob6.rec) && (!ob4IsIntersectingob6)){
            ob4IsIntersectingob6 = true;
            winner = faceOff(ob4, ob6);
            if (winner == 1){
                ob6.identity = ob4.identity;
            }
            else if (winner == 2){
                ob4.identity = ob6.identity;
            }
        }
        if (!(ob4.rec.intersects(ob6.rec))){
            ob4IsIntersectingob6 = false;
        }

        if (ob5.rec.intersects(ob6.rec) && (!ob5IsIntersectingob6)){
            ob5IsIntersectingob6 = true;
            winner = faceOff(ob5, ob6);
            if (winner == 1){
                ob6.identity = ob5.identity;
            }
            else if (winner == 2){
                ob5.identity = ob6.identity;
            }
        }
        if (!(ob5.rec.intersects(ob6.rec))){
            ob5IsIntersectingob6 = false;
        }
    }

        public int faceOff(Hero ob1, Hero ob2){
            if (ob1.identity == ob2.identity){
                return 0;
            }
            else if (ob1.identity == 1 && ob2.identity == 2){
                return 2;
            }
            else if (ob1.identity == 2 && ob2.identity == 3){
                return 2;
            }
            else if (ob1.identity == 3 && ob2.identity == 1){
                return 2;
            }
            else if (ob1.identity == 1 && ob2.identity == 3){
                return 1;
            }
            else if (ob1.identity == 2 && ob2.identity == 1){
                return 1;
            }
            else if (ob1.identity == 3 && ob2.identity == 2){
                return 1;
            }
            return 0;
        }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

        // setting up variables and methods for the astronaut, created in the class Hero w/
        // constructor class, moving in the move function, painting in setupGraphics
                // Where do I set up variables and methods for the astronaut? Where do I declare astro?
       // Where do I create astro? Where do I tell astro to move?
        // Where do  I paint onto the screen?
    }

}
