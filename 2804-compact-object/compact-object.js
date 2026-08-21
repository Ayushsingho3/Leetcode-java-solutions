var compactObject = function(obj) {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }
    
    if (Array.isArray(obj)) {
        const compactedArr = [];
        for (let i = 0; i < obj.length; i++) {
            if (Boolean(obj[i])) {
                compactedArr.push(compactObject(obj[i]));
            }
        }
        return compactedArr;
    }
    
    const compactedObj = {};
    for (const key in obj) {
        if (Boolean(obj[key])) {
            compactedObj[key] = compactObject(obj[key]);
        }
    }
    
    return compactedObj;
};