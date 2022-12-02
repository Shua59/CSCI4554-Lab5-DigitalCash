package DigitalCash;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DigitalCash {
    
    private String customerNumber;
    static final int NUM_CHUNKS = 10;
    static final int EXTRA_CHUNKS = 10; //generated to unblind for the bank

    //assuming communication Just Works


    public static void main(String[] args) {
        Bank b1 = new Bank("34190654", 
            new BigInteger("45102246070852877005834805071565697303530753565236670573550117115010865770545266090895421231898028549377566326963374616157756280120181748932018677751293313293523827859377666639166849915682609959979131340115018334098535898721356842525455183760413239157639236971732220612271453478131788159610221783610160493541"),
            new BigInteger("7"),
            10);
        
        try {
            ChunkBuilder b1Builder = new ChunkBuilder("SHA-256", "SHA-256", b1);
        } catch (NoSuchAlgorithmException noae) {
            System.err.println("Error generating ChunkBuilders: ");
            System.err.println(noae);
        }
    }

    public static BigInteger[][] openBill(Chunk[] bill, String halves){
        int[] halvesArray = Arrays.stream(halves.split("")).mapToInt(Integer::parseInt).toArray();
        BigInteger[][] output = new BigInteger[bill.length][3];
        Arrays.setAll(output, (int x) -> bill[x].openHalf(halvesArray[x]));
        return output;
    }

}