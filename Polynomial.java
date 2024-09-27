
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        this.coefficients = new double[] {0};
        this.exponents = new int[] {0};
    }

    public Polynomial(double[] c, int[] e) {
        this.coefficients = c;
        this.exponents = e;
    }

    public Polynomial(File f) {
        try (Scanner input = new Scanner(f)) {
            String line = input.nextLine();
            String[] terms = line.split("(?=-)|\\+");

            int[] newExp = new int[terms.length];
            double[] newCoeff = new double[terms.length];

            for (int i = 0; i < terms.length; i++) {

                if (terms[i].contains("x")) {
                    String[] coExp = terms[i].split("x");

                    /* Set coefficient array */
                    if (coExp[0].equals("")) {
                        newCoeff[i] = 1;
                    }
                    else if (coExp[0].equals("-")) {
                        newCoeff[i] = -1;
                    }
                    else {
                        double c = Double.parseDouble(coExp[0]);
                        newCoeff[i] = c;
                    }

                    /* Set exponent array */
                    if (coExp.length == 1) {
                        newExp[i] = 1;
                    }
                    else {
                        int e = Integer.parseInt(coExp[1]);
                        newExp[i] = e;
                    }
                }
                else {
                    newCoeff[i] = Double.parseDouble(terms[i]);
                    newExp[i] = 0;
                }
            }

            this.coefficients = newCoeff;
            this.exponents = newExp;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public Polynomial add(Polynomial p) {
        /* Find length of arrays in the new Polynomial */
        int same = 0;
        for (int i = 0; i < this.exponents.length; i++) {
            for (int j = 0; j < p.exponents.length; j++) {
                if (this.exponents[i] == p.exponents[j]) {
                    same++;
                }
            }
        }
        int length = this.exponents.length + p.exponents.length - same;

        int[] newExp = new int[length];
        double[] newCoeff = new double[length];

        /* Fill in exponent & coefficient array */
        int index = this.exponents.length;
        for (int i = 0; i < index; i++) {
            newExp[i] = this.exponents[i];
            newCoeff[i] = this.coefficients[i];
        }
        for (int i = 0; i < p.exponents.length; i++) {
            boolean match = false;
            for (int j = 0; j < index; j++) {
                if (newExp[j] == p.exponents[i]) {
                    match = true;
                    newCoeff[j] += p.coefficients[i];
                    break;
                }
            }
            if (!match) {
                newExp[index] = p.exponents[i];
                newCoeff[index] = p.coefficients[i];
                index++;
            }
        }

        /* Create a new Polynomial */
        Polynomial result = new Polynomial(newCoeff, newExp);
        return result;
    }

    public Polynomial multiply(Polynomial p) {
        int thisLen = this.exponents.length;
        Polynomial result = new Polynomial();
        int[] newExp = new int[p.exponents.length];
        double[] newCoeff = new double[p.exponents.length];

        for (int i = 0; i < thisLen; i++) {
            for (int j = 0; j < p.exponents.length; j++) {
                newExp[j] = p.exponents[j] + this.exponents[i];
                newCoeff[j] = p.coefficients[j] * this.coefficients[i];
            }
        Polynomial temp = new Polynomial(newCoeff, newExp);
        result = result.add(temp);
        }
        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double d) {
        return evaluate(d) == 0;
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (int i = 0; i < this.coefficients.length; i++) {

                if (i > 0 && this.coefficients[i] > 0) {
                    writer.write("+");
                }
                if (this.exponents[i] != 0 && this.coefficients[i] == -1) {
                    writer.write("-");
                }
                else if (this.coefficients[i] != 1 || this.exponents[i] == 0) {
                    if (this.coefficients[i] % 1 == 0) {
                        writer.write("" + (int) this.coefficients[i]);
                    }
                    else {
                        writer.write("" + this.coefficients[i]);
                    }
                }

                if (this.exponents[i] != 0) {
                    if (this.exponents[i] == 1) {
                        writer.write("x");
                    }
                    else {
                        writer.write("x" + this.exponents[i]);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error writing to file");
        }

    }
}