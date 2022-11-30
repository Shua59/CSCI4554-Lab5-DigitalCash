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

    //make lying version & hope that we can lie about what's in the boxes?  Or that it isn't checked right?
    //make "bad" chunks and send those (i.e. chunks that don't have our account number; it could have anything)
    //and lie about what's in them when the bank asks to see and sign them

    //We generate genuine bill
    //We give genuine bill to someone else for a genuine transaction.
    //They ask for proof to unblind the chunks correctly.
    //We unblind the relevant halves & have a bill "ready to spend"
    //Before actually giving them that bill, "we"/coconspirator cashes exactly that
    //give the now-spent bill
    //It looks like the other party is trying to cash a bill that's already been cashed, as though *they're* cheating



    public BigInteger[] openHalf(int half){
        if (half == 1){
            BigInteger[] output = {secureA, secureC, new BigInteger(secretY)};
            return output;
        } else {
            BigInteger[] output = {aXor, secureD, new BigInteger(secretX)};
            return output;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(finalHash);
    }
   
}