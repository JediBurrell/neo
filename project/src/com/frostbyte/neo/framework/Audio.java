package com.frostbyte.neo.framework;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * <strong><em>Audio</em></strong><br /><br />
 * 
 * An audio manager that holds a list of audio currently playing.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class Audio{
	
	private Map<String, Clip> clips = new HashMap<String, Clip>();
	
	/**
	 * <strong><em>playSound</em></strong><br /><br />
	 * 
	 * &emsp;Plays a .wav audio file located in /res/audio/.<br />
	 * &emsp;<em>E.g.</em> <sub>audio</sub><br /><br />
	 * &emsp;File location would be: <sub>/res/audio/audio.wav</sub><br />
	 * &emsp;and would be played automatically.
	 * 
	 * @param audio - File name.
	 */
	public void playSound(String audio){
		try{
		    AudioInputStream audioInputStream =
		        AudioSystem.getAudioInputStream(
		        	 getClass().getResource("/audio/"+audio+".wav"));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		    clip.addLineListener(new LineListener() {
				
				@Override
				public void update(LineEvent arg0) {
					if(arg0.getFramePosition()==clip.getFrameLength()){
						clip.close();
					}
				}
			});
		    clips.put(audio, clip);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * You shouldn't need this function, this class is for clips, not music.
	 * If you would like to play music, refer to the Music class.
	 */
	/**
	 * <strong><em>stopAudio</em></strong><br /><br />
	 * 
	 * &emsp;Stops audio.<br /><br />
	 * 
	 * <strong>Notes:</strong><br />
	 * &emsp;You shouldn't need this function, this class is for clips, not music.<br />
	 * &emsp;If you would like to play music, refer to the Music class.
	 * 
	 * @param audio - the title of the audio. <em>E.g.</em> <sub>audio</sub>
	 */
	public void stopAudio(String audio){
		clips.get(audio).stop();
		clips.get(audio).close();
	}
	
}