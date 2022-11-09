package DigitalCash;

import java.math.BigInteger;

public class Bank {
    String customerNumber;

    BigInteger publicModulo;
    BigInteger publicExponent;
    int keyRadix;
    //??? contactmethod (if there's an automated method for making requests)

    public Bank(String customerNumber, BigInteger publicModulo, BigInteger publicExponent, int keyRadix){
        this.customerNumber = customerNumber;
        this.publicExponent = publicExponent;
        this.publicModulo = publicModulo;
        this.keyRadix = keyRadix;
    }

    String getCustomerNumber(){
        return customerNumber;
    }

    int getRadix(){
        return keyRadix;
    }
}
