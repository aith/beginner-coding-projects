// Complex.java
// Ari Iramanesh
// 1628897
// Programming Assignment 6 - Complex Numbers
// Operates complex number expressions

class Complex{

   //--------------------------------------------------------------------------
   // Private Data Fields 
   //--------------------------------------------------------------------------
   private double re;
   private double im;
   
   //--------------------------------------------------------------------------
   // Public Constant Fields 
   //--------------------------------------------------------------------------
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

   //--------------------------------------------------------------------------
   // Constructors 
   //--------------------------------------------------------------------------
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

   Complex(String str){
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         re = Double.parseDouble(s);
         im = 0;
      }else if( s.matches(SGN+I) ){
         re = 0;
         im = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         re = 0;
         im = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         re = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         im = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         re = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         im = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
   }

   //---------------------------------------------------------------------------
   // Public methods 
   //---------------------------------------------------------------------------

   // Complex arithmetic -------------------------------------------------------

   // copy()
   // Return a new Complex equal to this Complex
   Complex copy(){
      Complex dupe = new Complex(re, im);
      dupe.re = this.re;
      dupe.im = this.im;
      return dupe;
   }
   
   // add()
   // Return a new Complex representing the sum this plus z.
   Complex add(Complex z){
      Complex added = new Complex(re, im);
      added.re = this.re + z.re;
      added.im = this.im + z.im;
      return added;
   }
   
   // negate()
   // Return a new Complex representing the negative of this.
   Complex negate(){
      Complex negated = new Complex(re, im);
      negated.re = 0 - this.re;
      negated.im = 0 - this.im;
      return negated;
   }

   // sub()
   // Return a new Complex representing the difference this minus z.
   Complex sub(Complex z){
      Complex subbed = new Complex(re, im);
      subbed.re = this.re-z.re;
      subbed.im = this.im-z.im;
      return subbed;
   }

   // mult()
   // Return a new Complex representing the product this times z.
   Complex mult(Complex z){
      Complex multed = new Complex(re, im);
      multed.re = this.re*z.re+(0-this.im*z.im);
      multed.im = this.im*z.re+this.re*z.im;
      return multed;
   }

   // recip()
   // Return a new Complex representing the reciprocal of this.
   // Throw an ArithmeticException with appropriate message if 
   // this.equals(Complex.ZERO).
   Complex recip(){
      if (this.equals(Complex.ZERO)) 
         throw new ArithmeticException("argument divisor is 0");
      Complex a = new Complex(re, im);
      a.re = this.re/(Math.pow(this.re, 2)+Math.pow(this.im, 2));
      a.im = (0-this.im)/(Math.pow(this.re, 2)+Math.pow(this.im, 2));
      return a;
   }

   // div()
   // Return a new Complex representing the quotient of this by z.
   // Throw an ArithmeticException with appropriate message if 
   // z.equals(Complex.ZERO).
   Complex div(Complex z){
      if (z.equals(Complex.ZERO))
         throw new ArithmeticException("argument divisor is 0");
      Complex a = this.mult(z.recip()); //REVIEW THIS, see bookmark if needed
      return a;
   }
   

   // conj()
   // Return a new Complex representing the conjugate of this Complex.
   Complex conj(){
      Complex a = new Complex(re, im);
      a.im = 0 - this.im;
      return a;
   }
   
   // Re()
   // Return the real part of this.
   double Re(){
      return re;
   }

   // Im()
   // Return the imaginary part of this.
   double Im(){
      return im;
   }

   // abs()
   // Return the modulus of this Complex, i.e. the distance between 
   // points (0, 0) and (re, im).
   double abs(){
      double mod = Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
      return mod;
   }

   // arg()
   // Return the argument of this Complex, i.e. the angle this Complex
   // makes with positive real axis.
   double arg(){
      return Math.atan2(im, re);
   }

   // Other functions ---------------------------------------------------------
   
   // toString()
   // Return a String representation of this Complex. The real and imaginary
   // parts will be rounded to 8 decimal places, and trailing zeros will be
   // truncated from the two parts. The String returned will be readable by 
   // the constructor Complex(String s)
   public String toString(){
      String fmt   = "%.8f";
      String real  = Double.toString(Double.parseDouble(String.format(fmt, re)));
      String imag  = Double.toString(Double.parseDouble(String.format(fmt, im)));
      String reStr = (
                        re==0 && im==0?
                           "0"
                        :re==0?
                           ""
                        :
                           real
                     );
      String imStr = (
                        im==0?
                           ""
                        :im==1?
                           "i"
                        :im==-1?
                           "-i"
                        :im<0 || re==0?
                           imag+"i"
                        :
                           "+"+imag+"i"
                     );
      return reStr+imStr;
   }

   // equals() 
   // Return true iff this and obj have the same real and imaginary parts.
   public boolean equals(Object obj){
      boolean eq = false;
      Complex a;
      if (obj instanceof Complex) {
         a = (Complex) obj;
         eq = (this.im == a.im && this.re == a.re);
      }
      return eq;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part b.
   static Complex valueOf(double a, double b){
      Complex n = new Complex(a, b);
      return n;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part 0.
   static Complex valueOf(double a){
      Complex n = new Complex(a);
      return n;
   }

   // valueOf()
   // Return a new Complex constructed from s.
   static Complex valueOf(String s){
      Complex n = new Complex(s);
      return n;
   }
}