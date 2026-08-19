var promiseAll = async function(functions) {
    return new Promise((resolve, reject) => {
        if (functions.length === 0) {
            resolve([]);
            return;
        }

        const results = new Array(functions.length);
        let resolvedCount = 0;

        functions.forEach((fn, i) => {
            fn()
                .then(val => {
                    results[i] = val;
                    resolvedCount++;
                    if (resolvedCount === functions.length) {
                        resolve(results);
                    }
                })
                .catch(error => {
                    reject(error);
                });
        });
    });
};