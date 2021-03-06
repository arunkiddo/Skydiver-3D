// Copyright 2014 Michael Scarlett
// All rights reserved

package com.scarlettapps.skydiver3d.worldview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.scarlettapps.skydiver3d.Skydiver3D;
import com.scarlettapps.skydiver3d.world.World;
import com.scarlettapps.skydiver3d.worldstate.Status;
import com.scarlettapps.skydiver3d.worldstate.StatusManager;
import com.scarlettapps.skydiver3d.worldview.ui.StatusView;

/**
 * The WorldView displays the World on the screen. It also allows
 * the player to control the game by listening to input and updating objects.
 */

public class WorldView {
	
	static final float CAM_OFFSET = 5f;
	
	private final StatusManager statusManager;
	private final Renderer renderer;	
	private final StatusView statusView;
		
	public WorldView(World world, StatusManager statusManager) {
		this.statusManager = statusManager;  
		
		DefaultShader.defaultCullFace = 0;
		
		renderer = new Renderer(world);
		statusView = new StatusView(statusManager.getStatus());
	}
	
	public void initialize() {
		statusView.initialize();
        renderer.initialize();
        renderer.switchState(statusManager, this);
	}
	
	public void update(float delta) {
		if (statusManager.switchState()) {
			renderer.switchState(statusManager, this);
		}
		renderer.update(delta);
		statusView.update(delta);
	}
	
	public void render(float delta) {
		renderer.render(delta);
		statusView.render(delta);
	}

	public void reset() {
		if (Skydiver3D.DEV_MODE) {
			Gdx.app.log(Skydiver3D.LOG, "Resetting WorldView");
		}
		
		statusView.reset();
        renderer.reset();
        renderer.switchState(statusManager, this);
	}

	public InputProcessor getInputProcessor() {
		return statusView.getInputProcessor();
	}
	
	StatusManager getStatusManager() {
		return statusManager;
	}
	
	StatusView getStatusView() {
		return statusView;
	}
	
	Renderer getRenderer() {
		return renderer;
	}

}
