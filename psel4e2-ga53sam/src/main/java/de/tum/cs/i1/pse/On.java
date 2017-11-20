package de.tum.cs.i1.pse;

//TODO: On State must conform to the State interface
public class On implements State{

	protected int computeVampirePower() {
		return 42;
	}

	private void LEDOn() {
		System.out.println("LED turned On.");
	}

	@Override
	public void entry() {
		LEDOn();

	}

	@Override
	public void exit() {
		computeVampirePower();
	}

	@Override
	public void handle(MusicPlayer player, Event event) {
		switch (event){
			case PlayPausePressed:
				player.setCurrentState(new PlayingMusic());
				break;
			case OnOffPressed:
				player.setCurrentState(new Inactive());
				break;
			case SleepTimeExceeded:
				break;
		}
	}
}
