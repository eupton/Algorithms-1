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
        //Double newRe = this.re * b.re - this.re * b.im;
        //Double newIm = this.im + b.im;
        //return new ComplexNumber(newRe, newIm);

        //the princeton file does it this way
        //I think they are using a negative to cancel out the i^2 that results from a.im * b.im
        //but I don't know for certain yet
        // Complex a = this;
        // double real = a.re * b.re - a.im * b.im;
        // double imag = a.re * b.im + a.im * b.re;
        // return new Complex(real, imag);
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
    // public Double getRealPart() { return Double.parseDouble(realNumber); }
    // public Double getImaginaryPart() { return Double.parseDouble(imaginaryNumber.replaceAll("[^0-9?!\\.]","")); }
    // public static ComplexNumber Add(ComplexNumber cNum1, ComplexNumber cNum2) {
    //     Double realPart = cNum1.getRealPart() + cNum2.getRealPart();
    //     Double imaginaryPart = cNum.getImaginaryPart() + cNum2.getImaginaryPart();

    //     ComplexNumber newCompNum = new ComplexNumber(realPart, imaginaryPart);
    // }

    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(11d, 3d);
        ComplexNumber b = new ComplexNumber(-5d, 9d);
        var d = ComplexNumber.distance(a, b);
        var m = ComplexNumber.midpoint(a, b);
        System.out.println(d);
        System.out.println(m.toString());
    }
}