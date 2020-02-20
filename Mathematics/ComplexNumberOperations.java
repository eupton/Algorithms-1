package mathematics;
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
        return realNumber + imaginaryNumber;
    }

    

    public Double getRealPart() { return Double.parseDouble(realNumber); }
    public Double getImaginaryPart() { return Double.parseDouble(imaginaryNumber.replaceAll("[^0-9?!\\.]","")); }
    public static ComplexNumber Add(ComplexNumber cNum1, ComplexNumber cNum2) {
        Double realPart = cNum1.getRealPart() + cNum2.getRealPart();
        Double imaginaryPart = cNum.getImaginaryPart() + cNum2.getImaginaryPart();

        ComplexNumber newCompNum = new ComplexNumber(realPart, imaginaryPart);
    }
}