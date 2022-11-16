package DigitalCash;

import java.math.BigInteger;
import java.util.Arrays;

public class Chunk {

    private BigInteger secureA;
    private BigInteger secureC;
    private BigInteger secureD;
    private BigInteger aXor;

    private byte[] secretX;
    private byte[] secretY;

    private byte[] finalHash;

    public Chunk(BigInteger secureA, BigInteger secureC, BigInteger secureD, BigInteger aXor, byte[] secretX, byte[] secretY, byte[] finalHash){
        
        this.secureA = secureA;
        this.secureC = secureC;
        this.secureD = secureD;

        this.aXor = aXor;

        this.secretX = secretX;
        this.secretY = secretY;
        this.finalHash = finalHash;
    }

    public byte[] getFinalHash(){
        return finalHash;
    }

    public String openHalfAsString(Boolean half){
        StringBuilder outputBuilder = new StringBuilder();
        if(half){
            outputBuilder.append("a_i: ");
            outputBuilder.append(secureA);
            outputBuilder.append("\nc_i: ");
            outputBuilder.append(secureC);
            outputBuilder.append("\ny_i: ");
            outputBuilder.append(Arrays.toString(secretY));
        } else {
            outputBuilder.append("I ⊕ a_i: ");
            outputBuilder.append(aXor);
            outputBuilder.append("\nd_i: ");
            outputBuilder.append(secureD);
            outputBuilder.append("\nx_i: ");
            outputBuilder.append(Arrays.toString(secretX));
        }
        outputBuilder.append("\n");
        return outputBuilder.toString();
    }

    public String openAllAsString(){
        return openHalfAsString(true) + openHalfAsString(false);
    }

    public BigInteger[] openHalf(int half){
        if (half == 1){
            BigInteger[] output = {secureA, secureC, new BigInteger(secretY)};
            return output;
        } else {
            BigInteger[] output = {aXor, secureD, new BigInteger(secretX)};
            return output;
        }
    }        
   
}