class Fancy {
    private long M;
    private long A;
    private java.util.List<Long> seq;
    private final long MOD = 1000000007;

    public Fancy() {
        M = 1;
        A = 0;
        seq = new java.util.ArrayList<>();
    }
    
    public void append(int val) {
        long x = ((val - A) % MOD + MOD) % MOD;
        x = (x * modInverse(M)) % MOD;
        seq.add(x);
    }
    
    public void addAll(int inc) {
        A = (A + inc) % MOD;
    }
    
    public void multAll(int m) {
        M = (M * m) % MOD;
        A = (A * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= seq.size()) {
            return -1;
        }
        long x = seq.get(idx);
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