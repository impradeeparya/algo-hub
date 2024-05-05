package algo.bloomfilter;


import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BloomFilter {

    private int size;
    private BitSet bitSet;
    private int hashFunctionCount;

    public BloomFilter(int size) {
        this.size = size;
        this.bitSet = new BitSet(this.size);
    }

    public BloomFilter(int size, int hashFunctionCount) {
        this.size = size;
        this.bitSet = new BitSet(this.size);
        this.hashFunctionCount = hashFunctionCount;
    }

    public void add(String element) {
        if (hashFunctionCount == 0) {
            bitSet.set(hash(element) % this.size);
        } else {
            IntStream.range(1, hashFunctionCount + 1).forEach(hashCount -> {
                int hash = hash(element, hashCount) % this.size;
                bitSet.set(hash);
            });
        }

    }

    public boolean contains(String element) {
        if (this.hashFunctionCount == 0) {
            return bitSet.get(hash(element) % this.size);
        } else {
            for (int hashCount = 1; hashCount <= this.hashFunctionCount; hashCount++) {
                boolean isPresent = bitSet.get(hash(element, hashCount) % this.size);
                if (!isPresent) {
                    return false;
                }
            }
            return true;
        }
    }

    private int hash(String element) {
        AtomicInteger hash = new AtomicInteger();
        element.chars().forEach(character -> {
            int newHash = (hash.get() + character);
            hash.set(newHash);
        });
        return hash.get();
    }

    private int hash(String element, int hashCount) {
        AtomicInteger hash = new AtomicInteger();
        element.chars().forEach(character -> {
            int newHash = (hash.get() + hashCount + character);
            hash.set(newHash);
        });
        return hash.get();
    }
}
