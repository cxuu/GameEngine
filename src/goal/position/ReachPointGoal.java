package goal.position;

import game_engine.collision.Boundary;
import game_engine.transition.WinStatus;
import game_object.character.ICharacter;
import game_object.core.Dimension;
import game_object.core.Position;
import goal.GoalType;

public class ReachPointGoal extends PositionGoal {

	public ReachPointGoal(ICharacter character, Position pos) {
		super(character, pos);
	}

	@Override
	public boolean checkGoal() {
	        Boundary charBoundary = new Boundary(myCharacter.getPosition(),myCharacter.getDimension());
	        Boundary goalBoundary = new Boundary(myPos,new Dimension(0,0));
		return charBoundary.overlaps(goalBoundary);
		/*
	        double height = myCharacter.getDimension().getHeight();
		double width = myCharacter.getDimension().getWidth();
		double x = myCharacter.getPosition().getX();
		double y = myCharacter.getPosition().getY();
		return (myPos.getX() < x + width / 2 && 
				myPos.getX() > x - width / 2 && 
				myPos.getY() < y + height / 2 && 
				myPos.getY() > x - height / 2);
				*/
	}
	
	@Override
	public final GoalType getGoalType() {
		return GoalType.REACH_POINT;
	}
	
	@Override
	public WinStatus getResult() {
		return WinStatus.WON;
	}

}
