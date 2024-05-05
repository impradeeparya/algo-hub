package algo.bloomfilter;

public class BloomFilterRunner {

    public static void main(String[] args) {
        System.out.println("############################# Single hash function #############################");
        BloomFilter bloomFilter = new BloomFilter(8);
        bloomFilter.add("alpha");
        bloomFilter.add("beta");

        System.out.println("alpha: " + bloomFilter.contains("alpha"));
        System.out.println("beta: " + bloomFilter.contains("beta"));
        System.out.println("gamma: " + bloomFilter.contains("gamma"));

        System.out.println("############################# Multiple hash function #############################");
        bloomFilter = new BloomFilter(8, 3);
        bloomFilter.add("alpha");
        bloomFilter.add("beta");

        System.out.println("alpha: " + bloomFilter.contains("alpha"));
        System.out.println("beta: " + bloomFilter.contains("beta"));
        System.out.println("gamma: " + bloomFilter.contains("gamma"));
    }
}
