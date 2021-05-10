import { UITabbedPanel, UISpan } from './libs/ui.js';

import { SidebarScene } from './Sidebar.Scene.js';
import { SidebarCompare } from './Sidebar.Compare.js';
import { SidebarProperties } from './Sidebar.Properties.js';
// import { SidebarScript } from './Sidebar.Script.js';
// import { SidebarAnimation } from './Sidebar.Animation.js';
import { SidebarProject } from './Sidebar.Project.js';
import { SidebarHistory } from './Sidebar.History.js';
import { SidebarSettings } from './Sidebar.Settings.js';

function Sidebar( editor ) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId( 'sidebar' );

	var scene = new UISpan().add(
		new SidebarScene( editor ),
		new SidebarProperties( editor ),
		// new SidebarAnimation( editor ),
		// new SidebarScript( editor )
	);

	let compare = new SidebarCompare(editor);

	var project = new SidebarProject( editor );

	var settings = new UISpan().add(
		new SidebarSettings( editor ),
		new SidebarHistory( editor )
	);

	container.addTab( 'scene', strings.getKey( 'sidebar/scene' ), scene );
	container.addTab( 'project', strings.getKey( 'sidebar/project' ), project );
	container.addTab( 'compare', strings.getKey( 'sidebar/compare' ), compare );
	container.addTab( 'settings', strings.getKey( 'sidebar/settings' ), settings );
	container.select( 'scene' );

	return container;

}

export { Sidebar };
