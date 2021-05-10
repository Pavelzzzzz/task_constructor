
function Comparator( ) {

	this.inMemory = false;
	this.updatable = false;
	this.comparisonObject = '';
	this.type = '';
	this.name = '';

}

Comparator.prototype.compare = function (source, target) {

	return source.type === target.type;
};

export { Comparator };
