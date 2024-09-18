public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[] {0};
    }

    public Polynomial(double[] c) {
        this.coefficients = c;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result;
        if (this.coefficients.length > p.coefficients.length) {
            result = new Polynomial(this.coefficients);
            for (int i = 0; i < p.coefficients.length; i++) {
                result.coefficients[i] = this.coefficients[i] + p.coefficients[i];
            }
        }
        else {
            result = new Polynomial(p.coefficients);
            for (int i = 0; i < this.coefficients.length; i++) {
                result.coefficients[i] = this.coefficients[i] + p.coefficients[i];
            }
        }
        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double d) {
        return evaluate(d) == 0;
    }
}