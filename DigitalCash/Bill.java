package DigitalCash;
public class Bill {

    int billNumber;
    Chunk[] chunks;
    
    private Bill(Chunk[] chunks){
        this.chunks = chunks;
    }

    public static Bill generateBill(ChunkBuilder b, int billNumber, int billSize){
        return new Bill(b.generateBill(billSize));
    }
}
