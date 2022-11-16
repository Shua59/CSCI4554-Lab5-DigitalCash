package DigitalCash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ChunkBuilder {
    private MessageDigest fHash;
    private MessageDigest gHash;

    private SecureRandom seedGen = new SecureRandom();

    private SecureRandom billNumberGenerator = new SecureRandom(seedGen.generateSeed(8));
    private SecureRandom aKey = new SecureRandom(seedGen.generateSeed(8));
    private SecureRandom cKey = new SecureRandom(seedGen.generateSeed(8));
    private SecureRandom dKey = new SecureRandom(seedGen.generateSeed(8));

    private Bank bank;

    
    public ChunkBuilder(String hashFName, String hashGName, Bank bank) throws NoSuchAlgorithmException{
        fHash = MessageDigest.getInstance(hashFName);
        gHash = MessageDigest.getInstance(hashGName);
    }

    public Chunk genChunk(BigInteger billNumber){
        BigInteger secureA = new BigInteger(128, aKey);
        BigInteger secureC = new BigInteger(128, cKey);
        BigInteger secureD = new BigInteger(128, dKey);

        //this is a very, very ugly mess of type conversion, but we need to concatenate
        //the two numbers.
        BigInteger messageMask = new BigInteger(bank.getCustomerNumber() + billNumber, bank.getRadix());

        byte[] secretX = gHash.digest(concatenateArrays(secureA.toByteArray(), secureC.toByteArray()));
        byte[] secretY = gHash.digest(concatenateArrays(messageMask.xor(secureA).toByteArray(), secureD.toByteArray()));
        byte[] finalHash = fHash.digest(concatenateArrays(secretX, secretY));
        
        return new Chunk(secureA, secureC, secureD, messageMask, secretX, secretY, finalHash);
    }

    public Chunk genChunk(){
        return genChunk(new BigInteger(billNumberGenerator, ));
    }

    private byte[] concatenateArrays(byte[] a, byte[] b){
        byte[] output = new byte[a.length + b.length];
        int position = 0;
        for(int i = 0; i < a.length; i++){
            output[position] = a[i];
            position++;
        }
        for(int j = 0; j < b.length; j++){
            output[position] = b[j];
            position++;
        }
        return output;
    }

}
