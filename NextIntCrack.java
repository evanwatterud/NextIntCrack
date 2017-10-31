import java.util.Random;
import java.math.BigInteger;

public class NextIntCrack {
    public static void main(String[] args) {
        long multiplier = 0x5DEECE66DL;
        long addend = 0xBL;
        long mask = (1L << 48) - 1;
        Random random = new Random();
        long v1 = random.nextInt();
        long v2 = random.nextInt();
        System.out.println(v1);
        System.out.println(v2);
        for (int i = 0; i < 65536; i++) {
            String addedBits = String.format("%16s", Long.toBinaryString(i)).replace(' ', '0');
            long seed = new BigInteger(Long.toBinaryString(v1) + addedBits, 2).longValue();
            if ((int)(((seed * multiplier + addend) & mask) >>> 16) == v2) {
                System.out.println("Seed found: " + seed);
                System.out.println("Predicted int: " + (int)(((((seed * multiplier + addend) & mask) * multiplier + addend) & mask) >>> 16));
                System.out.println("Actual int: " + random.nextInt());
                break;
            }
        }
    }
}
