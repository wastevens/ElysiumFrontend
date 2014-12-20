function BiDirectionalMap () {
	this.keysToValues = {};
	this.valuesToKeys = {};
}

BiDirectionalMap.prototype.with = function(key, value) {
	this.keysToValues[key] = value;
	this.valuesToKeys[value] = key;
	return this;
};

BiDirectionalMap.prototype.valueFor = function(key) {
	return this.keysToValues[key];
};

BiDirectionalMap.prototype.keyFor  = function(value) {
	return this.valuesToKeys[value];
};
