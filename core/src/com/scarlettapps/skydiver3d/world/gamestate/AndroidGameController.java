// Copyright 2014 Michael Scarlett
// All rights reserved

package com.scarlettapps.skydiver3d.world.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AndroidGameController extends GameController {

	private static final float SENSITIVITY = 0.3f;
	
	public AndroidGameController() {
		if (!Gdx.input.isPeripheralAvailable(Peripheral.Compass)) {
			throw new GdxRuntimeException("Compass not available");
		}
		
		Gdx.input.setCatchBackKey(false);
		Gdx.input.setCatchMenuKey(true);
	}
	
	@Override
	public void update(float delta) {
		ax = -Gdx.input.getPitch()*SENSITIVITY;
		//ay = Gdx.input.getRoll()*SENSITIVITY;
		ay = 0;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		faster = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		faster = false;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
