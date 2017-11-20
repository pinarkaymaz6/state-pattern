package de.tum.cs.i1.pse;

//TODO: Inactive State must conform to the State interface
public class Inactive implements State{

	protected int computeVampirePower() {
		return 42;
	}

	private void LEDOff() {
		System.out.println("LED turned Off.");
	}

	@Override
	public void entry() {
		LEDOff();
	}

	@Override
	public void exit() {
		computeVampirePower();
	}

	@Override
	public void handle(MusicPlayer player, Event event) {
		switch (event){
			case PlayPausePressed:
				break;
			case OnOffPressed:
				player.setCurrentState(new On());
				break;
			case SleepTimeExceeded:
				break;
		}

	}
}
