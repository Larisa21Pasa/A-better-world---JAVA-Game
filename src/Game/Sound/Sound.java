package Game.Sound;

import Game.Entity.Player;
import Game.Entity.PlayerController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL [] = new URL[30];
    static FloatControl floatControl;
    public static int VolumeScale = 3;
    public  static float  volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/SoundsPackage/love_song.wav");   //piesa de fundal
        soundURL[1] = getClass().getResource("/SoundsPackage/you_lose.wav");    // pentru "Game over"
        soundURL[2] = getClass().getResource("/SoundsPackage/congratulations.wav");     //pentru castigarea nivelului
        soundURL[3] = getClass().getResource("/SoundsPackage/COIN.wav");        //adunarea unui banut
        soundURL[4] = getClass().getResource("/SoundsPackage/ouch0.wav");       // la coliziunea inamicului cu munitia
        soundURL[5] = getClass().getResource("/SoundsPackage/sfx_04a.wav");
        //jump
        soundURL[6] = getClass().getResource("/SoundsPackage/sfx_jump.wav");
        soundURL[7] = getClass().getResource("/SoundsPackage/sfx_interact.wav");
        soundURL[8] = getClass().getResource("/SoundsPackage/level_up.wav");
    }
    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkMusicVolume();
        }catch (Exception e){}
    }
    public void PlayMusic( int i) {
        this.setFile(i);
        this.play();
        this.loop();
    }
    public void StopMusic()
    {
        this.stop();
    }
    public void PlaySoundsEffect(int i) {
        this.setFile(i);
        this.play();
    }
    public static void checkMusicVolume() {
        switch (VolumeScale) {
            case 0: volume = -80f; break;
            case 1:volume=-20f; break;
            case 2:volume=-12f; break;
            case 3: volume=-5f;break;
            case 4:volume=1f ;break;
            case 5: volume=6f;break;
        }
        floatControl.setValue(volume);
    }

    public void play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
