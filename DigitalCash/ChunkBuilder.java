package DigitalCash;

import java.math.BigInteger;
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




        byte secretX = fHash
    }

}
