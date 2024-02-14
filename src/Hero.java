import java.awt.*;

public class Hero {
    // Variable declarations
    public String name;
    public int xpos;
    public int ypos;
    public int dx; // Speed in the x direction
    public int dy; // Speed in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    // the rectangle is like a hitbox
    public Rectangle rec;
    public int identity;

    public static void main(String[] args) {

    }

    public Hero(int pXpos, int pYpos, int pDx, int pDy, int pIdentity){
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx; // one pixel per millisecond or the pause time
        dy = pDy;
        width = 70;
        height = 80;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        identity = pIdentity;
    }

    public void move() {
        xpos += dx;
        ypos -= dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void wrappingMove(){
        if (xpos > 1000){
            xpos = 0;
        }
        if (xpos < 0) {
             xpos = 1000;
        }
        if (ypos > 700) {
            ypos = 0;
        }
        if (ypos < 0) {
            xpos = 700;
        }

        // Last two lines are to update the position, makes the object move
       xpos = xpos + dx;
       ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void bouncingMove(){
        if (xpos > 940 || xpos < 0) {
            dx = -dx;
        }

        if (ypos > 640 || ypos < 0){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        // Update rectangle location
        rec = new Rectangle(xpos, ypos, width, height);
    }



    public void printInfo(){
        System.out.println("This hero is at " + xpos + ", " + ypos + " || dx and dy = " + dx + " " + dy +
                " || width and height = " + width + " " + height + " || isAlive is " + isAlive);
    }
}
