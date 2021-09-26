
public class AutoboxingDemo {
    public static void main(String args[]) {
        //We write
        Integer number = 100;

        //what happens behind the scenes (<1.5)
        Integer number = Integer.valueOf(100);

        // We write
        Integer num2 = new Integer(50);
        int inum = num2;

        //what happens behind the scenes (<1.5)
        Integer num2 = new Integer(50);
        int inum = num2.intValue();
    }
}
