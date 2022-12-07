package DigitalCash;

import java.math.BigInteger;
import java.util.ArrayList;
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


    public Bill fullyOpenBy(String positions){
        StringBuilder revealedChunks = new StringBuilder();
        ArrayList<Chunk> signedChunks = new ArrayList<Chunk>();
        
        char[] positionArray = positions.toCharArray();
        
        if(positionArray.length != chunks.length){
            throw new IllegalArgumentException("Requires a string of equal length to the number of chunks.");
        }

        for(int i = 0; i < chunks.length; i++){
            if (positionArray[i] == '1') {
                revealedChunks.append("Chunk ");
                revealedChunks.append(i);
                revealedChunks.append(": ");
                revealedChunks.append(chunks[i].toString());
                revealedChunks.append("\n");
                revealedChunks.append(chunks[i].openForBank());
            } else {
                signedChunks.add(chunks[i]);
            }
        }
        System.out.println(revealedChunks.toString());
        return new Bill((Chunk[]) signedChunks.toArray(), billNumber);
    }

    @Override
    public String toString(){
        return Arrays.toString(chunks);
    }
}
