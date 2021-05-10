import { UIPanel, UIBreak} from './libs/ui.js';
import { UIOutliner} from './libs/ui.three.js';
import {UIButton, UIRow} from "./libs/ui";
import {Vector3} from "three";
import {LineComparator} from "./comparators/LineComparator";

function SidebarCompare( editor ) {

	let imitable = {
		camera : editor.camera.clone(),
		scene : editor.scene.clone()
	};


	var signals = editor.signals;

	var container = new UIPanel();
	container.setBorderTop( '0' );
	container.setPaddingTop( '20px' );

	// outliner

	var nodeStates = new WeakMap();

	function buildOption( object, draggable ) {

		var option = document.createElement( 'div' );
		option.draggable = draggable;
		option.innerHTML = buildHTML( object );
		option.value = object.id;

		// opener

		if ( nodeStates.has( object ) ) {

			var state = nodeStates.get( object );

			var opener = document.createElement( 'span' );
			opener.classList.add( 'opener' );

			if ( object.children.length > 0 ) {

				opener.classList.add( state ? 'open' : 'closed' );

			}

			opener.addEventListener( 'click', function () {

				nodeStates.set( object, nodeStates.get( object ) === false ); // toggle
				refreshUI();

			}, false );

			option.insertBefore( opener, option.firstChild );

		}

		return option;

	}

	function getMaterialName( material ) {

		if ( Array.isArray( material ) ) {

			var array = [];

			for ( var i = 0; i < material.length; i ++ ) {

				array.push( material[ i ].name );

			}

			return array.join( ',' );

		}

		return material.name;

	}

	function escapeHTML( html ) {

		return html
			.replace( /&/g, '&amp;' )
			.replace( /"/g, '&quot;' )
			.replace( /'/g, '&#39;' )
			.replace( /</g, '&lt;' )
			.replace( />/g, '&gt;' );

	}

	function getObjectType( object ) {

		if ( object.isScene ) return 'Scene';
		if ( object.isCamera ) return 'Camera';
		if ( object.isLight ) return 'Light';
		if ( object.isMesh ) return 'Mesh';
		if ( object.isLine ) return 'Line';
		if ( object.isPoints ) return 'Points';

		return 'Object3D';

	}

	function buildHTML( object ) {

		var html = `<span class="type ${ getObjectType( object ) }"></span> ${ escapeHTML( object.name ) }`;

		if ( object.isMesh ) {

			var geometry = object.geometry;
			var material = object.material;

			html += ` <span class="type Geometry"></span> ${ escapeHTML( geometry.name ) }`;
			html += ` <span class="type Material"></span> ${ escapeHTML( getMaterialName( material ) ) }`;

		}

		html += getScript( object.uuid );

		return html;

	}

	function getScript( uuid ) {

		if ( editor.scripts[ uuid ] !== undefined ) {

			return ' <span class="type Script"></span>';

		}

		return '';

	}

	var outliner = new UIOutliner( editor );
	outliner.setId( 'outliner' );
	outliner.onChange( function () {



	} );
	container.add( outliner );
	container.add( new UIBreak() );

	// buttons

	// load

	var buttonsRow = new UIRow();

	let loadImitableObject = new UIButton().setLabel( editor.strings.getKey( 'sidebar/compare/load' ) ).setMargin( '0px 5px' );

	var fileInput = document.createElement( 'input' );
	fileInput.multiple = true;
	fileInput.type = 'file';
	fileInput.addEventListener( 'change', function () {

		editor.loader.loadFiles( fileInput.files );
		// form.reset();

	} );

	loadImitableObject.onClick( function () {

		fileInput.click();

	} );
	buttonsRow.add(loadImitableObject);

	// compare

	let compareWithImitableObject = new UIButton().setLabel( editor.strings.getKey( 'sidebar/compare/compare' ) ).setMargin( '0px 5px' );
	compareWithImitableObject.onClick( function () {
		compare(editor.scene);
	} );
	buttonsRow.add(compareWithImitableObject);

	container.add(buttonsRow);

	function compare (target) {

		let source = {
			type: "Line",
			name: "Line",
			position: new Vector3(),
			scale: new Vector3(1, 1, 1),
			rotation: new Vector3(1, 1, 1)
		};
		let comparator = new LineComparator();
		return comparator.compare(source, target);
	}

	function refreshUI() {

		var camera = imitable.camera;
		var scene = imitable.scene;

		var options = [];

		options.push( buildOption( camera, false ) );
		options.push( buildOption( scene, false ) );

		( function addObjects( objects, pad ) {

			for ( var i = 0, l = objects.length; i < l; i ++ ) {

				var object = objects[ i ];

				if ( nodeStates.has( object ) === false ) {

					nodeStates.set( object, false );

				}

				var option = buildOption( object, true );
				option.style.paddingLeft = ( pad * 18 ) + 'px';
				options.push( option );

				if ( nodeStates.get( object ) === true ) {

					addObjects( object.children, pad + 1 );

				}

			}

		} )( scene.children, 0 );

		outliner.setOptions( options );

	}


	refreshUI();

	// events

	signals.editorCleared.add( refreshUI );

	signals.sceneGraphChanged.add( refreshUI );

	return container;

}

export { SidebarCompare };
