class TimeLimitedCache {
    constructor() {
        this.cache = new Map();
    }

    set(key, value, duration) {
        const hasKey = this.cache.has(key);
        if (hasKey) {
            clearTimeout(this.cache.get(key).timerId);
        }
        
        const timerId = setTimeout(() => {
            this.cache.delete(key);
        }, duration);
        
        this.cache.set(key, { value, timerId });
        return hasKey;
    }

    get(key) {
        if (this.cache.has(key)) {
            return this.cache.get(key).value;
        }
        return -1;
    }

    count() {
        return this.cache.size;
    }
}