class Fancy {
    private long M;
    private long A;
    private final long MOD = 1000000007;
    private int[] seq;
    private int size;

    public Fancy() {
        M = 1;
        A = 0;
        seq = new int[100005];
        size = 0;
    }
    
    public void append(int val) {
        long x = ((val - A) % MOD + MOD) % MOD;
        x = (x * modInverse(M)) % MOD;
        seq[size++] = (int) x;
    }
    
    public void addAll(int inc) {
        A = (A + inc) % MOD;
    }
    
    public void multAll(int m) {
        M = (M * m) % MOD;
        A = (A * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= size) {
            return -1;
        }
        long x = seq[idx];
        long res = (x * M + A) % MOD;
        return (int) res;
    }
    
    private long modInverse(long n) {
        return power(n, MOD - 2);
    }
    
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}