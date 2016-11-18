package goal.health;

import game_object.character.ICharacter;
import goal.AbstractGoal;

public abstract class HealthGoal extends AbstractGoal{
	
	protected ICharacter myCharacter;
	
	public HealthGoal(ICharacter character) {
		 myCharacter = character;
	}
	
	@Override
	public abstract boolean checkGoal();

	@Override
	protected abstract void setResult();

}
