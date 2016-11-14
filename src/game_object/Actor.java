package game_object;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;

public interface Actor {
	void moveLeft();
	void moveRight();
	void moveUp();
	void moveDown();
	void jumpUp();
	void act();
	void fire();
	
	ArrayList<ActionTrigger> getActionTriggers();
	void setActionTriggers(ArrayList<ActionTrigger> ats);
	
}
