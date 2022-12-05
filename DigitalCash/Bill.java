package DigitalCash;

import java.math.BigInteger;
import java.util.Arrays;

public class Bill {

    BigInteger billNumber;
    Chunk[] chunks;
    
    private Bill(Chunk[] chunks, BigInteger billNumber){
        this.chunks = chunks;
        this.billNumber = billNumber;
    }

    public static Bill from(ChunkBuilder b, BigInteger billNumber, int billSize){
        Bill output = new Bill(b.generateBill(billSize, billNumber), billNumber);

        return output;
    }

    public BigInteger getBillNumber(){
        return billNumber;
    }

    public BigInteger[][] open(String halves){
        int[] halvesArray = Arrays.stream(halves.split("")).mapToInt(Integer::parseInt).toArray();
        BigInteger[][] output = new BigInteger[chunks.length][3];
        Arrays.setAll(output, (int x) -> chunks[x].openHalf(halvesArray[x]));
        return output;
    }

    @Override
    public String toString(){
        return Arrays.toString(chunks);
    }
}
