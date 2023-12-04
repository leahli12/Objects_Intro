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

    public static void main(String[] args) {

    }

    public Hero(int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        dx = 1; // one pixel per millisecond or the pause time
        dy = 0;
        width = 60;
        height = 80;
        isAlive = true;
    }

    public void printInfo(){

    }
}
