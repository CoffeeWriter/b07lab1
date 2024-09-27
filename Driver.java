
import java.io.File;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6, -2, 5};
        int [] e1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {1, 2};
        int [] e2 = {0, 2};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s1 = p1.add(p2);
        System.out.println("s1(1) = " + s1.evaluate(1));
        if(s1.hasRoot(1))
            System.out.println("1 is a root of s1");
        else
            System.out.println("1 is not a root of s1");

        
        /* (1 + x)(1 + 5x + 3x^2) */
        double [] c3 = {1, 1};
        int [] e3 = {0, 1};
        double [] c4 = {1, 5, 3};
        int [] e4 = {0, 1, 2};
        Polynomial p3 = new Polynomial(c3, e3);
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial s2 = p3.multiply(p4);
        System.out.println("s2(1) = " + s2.evaluate(2));
        if(s2.hasRoot(-1))
            System.out.println("-1 is a root of s2");
        else
            System.out.println("-1 is not a root of s2");

        /* 5-x+3x2-x4+7x8+x9 */
        Polynomial fp = new Polynomial(new File("poly"));
        System.out.println("fp(-1) = " + fp.evaluate(-1));

        fp.saveToFile("result");
    }
}