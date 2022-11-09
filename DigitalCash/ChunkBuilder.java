package DigitalCash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

public class ChunkBuilder {
    private MessageDigest fHash;
    private MessageDigest gHash;

    private SecureRandom seedGen = new SecureRandom();

    private SecureRandom aKey = new SecureRandom(seedGen.generateSeed(8));
    private SecureRandom cKey = new SecureRandom(seedGen.generateSeed(8));
    private SecureRandom dKey = new SecureRandom(seedGen.generateSeed(8));

    private String customerNumber;

    
    public ChunkBuilder(String hashFName, String hashGName, String customerNumber) throws NoSuchAlgorithmException{
        fHash = MessageDigest.getInstance(hashFName);
        gHash = MessageDigest.getInstance(hashGName);
        this.customerNumber = customerNumber;
    }

    public Chunk genChunk(String billNumber){
        
    }

}
