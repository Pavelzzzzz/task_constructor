import { Comparator } from '../Comparator.js';

function LineComparator() {

	Comparator.call( this );

	this.type = 'LineComparator';
	this.comparisonObject = 'Line';

}

LineComparator.prototype = {
	compare: function (source, target) {
		console.log('Source -> ', source);
		console.log('Target -> ', target);

		console.log('type -> ', source.type === target.type);
		console.log('position -> ', source.position.equals(target.position));
		console.log('scale -> ', source.scale.equals(target.scale));
		console.log('rotation -> ', source.rotation.equals(target.rotation.toVector3()));

		return source.type === target.type
			& source.position.equals(target.position)
			& source.scale.equals(target.scale)
			& source.rotation.equals(target.rotation.toVector3());
	}

};

export { LineComparator };
