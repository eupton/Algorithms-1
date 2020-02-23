import java.text.DecimalFormat;

/*
* A class I made to cement my understanding of complex numbers while also practicing java fundamentals 
* after developing this class I also came across the Complex.java class created by princeton cs professor Robert Sedgewick
* see https://introcs.cs.princeton.edu/java/32class/Complex.java.html
* I wasn't too far from their implementation
*/
public class ComplexNumber {
    public ComplexNumber(Double re, Double im) {
        this.re = re;
        this.im = im;
    }

    private Double re;
    private Double im;

    public Double getRe() { return this.re; }
    public Double getIm() { return this.im; }

    public String toString() {
        String sign = (im >= 0) ? "+" : "-";
        DecimalFormat decFormat = new DecimalFormat("0.#");
        return decFormat.format(re) + " " + sign + " " + decFormat.format(Math.abs(im)) + "i";
    }

    public ComplexNumber add(ComplexNumber b) {
        Double newRe = this.re + b.re;
        Double newIm = this.im + b.im;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber subtract(ComplexNumber b) {
        Double newRe = this.re - b.re;
        Double newIm = this.im - b.im;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber multiply(ComplexNumber b) {
        // to get the real number, multiply the real numbers then the imaginary, 
        // but subtract to reverse the number sign of the product because the i^2 = -1
        Double newReal = this.re * b.re - this.im * b.im; 
        Double newI = this.re * b.im + this.im * b.re;
        return new ComplexNumber(newReal, newI);
    }

    public ComplexNumber divide(ComplexNumber b) {
        ComplexNumber conj = b.conjugate();
        ComplexNumber n = this.multiply(conj);
        ComplexNumber d = this.multiply(conj);

        return new ComplexNumber(n.re/d.re, n.im/d.re);
    }

    //invert the sign of the imaginary number to produce 
    //the conjugate of the original complex number
    //complex numbers multiplied by their conjugate result in a Real number
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.re, this.im * -1);
    }

    public static Double distance(ComplexNumber a, ComplexNumber b) {
        Double re;
        Double im;
        if(a.re <= b.re){
            re = (b.re - a.re);
        }
        else {//(a.re >= b.re) {
            re = (a.re - b.re);
        } 

        if(a.im <= b.im){
            im = (b.im - a.im);
        }
        else {//(a.im >= b.im) {
            im = (a.im - b.im);
        }

        re = Math.pow(re, 2.0);
        im = Math.pow(im, 2.0);
        Double d = Math.sqrt(re + im);
        return d;
    }

    public static ComplexNumber midpoint(ComplexNumber a, ComplexNumber b) {
        Double re = (a.re + b.re)/2;
        Double im = (a.im + b.im)/2;

        ComplexNumber m = new ComplexNumber(re, im);

        return m;
    }

    //create a complex number from a string "2 + 3i" or "-20 - 7i"
    public static ComplexNumber parse(String complexString) {
        return new ComplexNumber(0d,0d);
    }

    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(2d, 4d);
        ComplexNumber b = new ComplexNumber(3d, -2d);
        var quotient = a.divide(b);
        var product = a.multiply(b);
        System.out.println(quotient);
        System.out.println(product);
    }
}