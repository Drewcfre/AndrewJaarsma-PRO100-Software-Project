/**
 * @author znye
 * @createdOn 8/1/2024 at 7:36 PM
 * @projectName PRO100-Software-Project
 * @packageName controller;
 */
package controller;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.FileInputStream;


import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.UniversalAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class AudioController {
    public static AudioDispatcher dispatcher;
    public float pitchInHz;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    public void getPitchFromFile(String filePath) {
        try {
            releaseDispatcher(dispatcher);

            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat baseFormat = audioStream.getFormat();
            TarsosDSPAudioFormat tarsosDSPAudioFormat = new TarsosDSPAudioFormat(baseFormat.getSampleRate() * 4, 16, 1, true, false);

            System.out.println("Audio Format: " + baseFormat);


            int bufferSize = 2048;
            int overlap = 1280;

            dispatcher = new AudioDispatcher(new UniversalAudioInputStream(new FileInputStream(filePath), tarsosDSPAudioFormat), bufferSize, overlap);


            final AudioProcessor playerProcessor = new AudioPlayer(tarsosDSPAudioFormat);

            dispatcher.addAudioProcessor(playerProcessor);

            AudioProcessor pitchProcessor = getAudioProcessor(tarsosDSPAudioFormat, bufferSize);
            dispatcher.addAudioProcessor(pitchProcessor);

            Thread audioThread = new Thread(dispatcher, "Audio Thread");
            audioThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private AudioProcessor getAudioProcessor(TarsosDSPAudioFormat tarsosDSPAudioFormat, int bufferSize) {
        PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {

            public void handlePitch(final PitchDetectionResult res, AudioEvent e) {
                pitchInHz = res.getPitch();
                processPitch(pitchInHz);
            }

            public void processPitch(float pitchInHz) {

                float lastPitch = 0;
                if (pitchInHz != -1) {
//                    System.out.println(pitchInHz - 50000);
                }
                float maxHz = 1150;
                if (pitchInHz > maxHz) {
                    pitchInHz = pitchInHz - 50000;
                }

//                if(lastPitch >= pitchInHz||lastPitch<=pitchInHz) {
//
//                    if (pitchInHz >= 500 && pitchInHz < 662.5) {
//                        System.out.print("AAAA");
//                    } else if (pitchInHz >= 662.5 && pitchInHz < 825) {
//                        System.out.print("BBBB");
//                    } else if (pitchInHz >= 825 && pitchInHz < 987.5) {
//                        System.out.print("CCCC");
//                    } else if (pitchInHz >= 987.5 && pitchInHz < 1150) {
//                        System.out.print("DDDD");
//                    }
//                }else{
                if (pitchInHz >= 500 && pitchInHz < 662.5) {
                    if (a >= 2) {
                        System.out.print("A");
                    } else System.out.println("\nA");
                    a++;
                    b = 0;
                    c = 0;
                    d = 0;
                } else if (pitchInHz >= 662.5 && pitchInHz < 825) {
                    if (b >= 2) {
                        System.out.print("B");
                    } else System.out.println("\nB");
                    b++;
                    a = 0;
                    c = 0;
                    d = 0;
                } else if (pitchInHz >= 825 && pitchInHz < 987.5) {
                    if (c >= 2) {
                        System.out.print("C");
                    } else System.out.println("\nC");
                    c++;
                    b = 0;
                    a = 0;
                    d = 0;
                } else if (pitchInHz >= 987.5 && pitchInHz < 1150) {
                    if (d >= 2) {
                        System.out.print("D");
                    } else System.out.println("\nD");
                    d++;
                    b = 0;
                    c = 0;
                    a = 0;
                }
//                }
//                lastPitch = pitchInHz;
            }
        };

//        AudioProcessor pitchProcessor =
        return new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, tarsosDSPAudioFormat.getSampleRate(), bufferSize, pitchDetectionHandler);
//        return pitchProcessor;
    }

    public void releaseDispatcher(AudioDispatcher dispatcher) {
        if (dispatcher != null) {
            if (!dispatcher.isStopped())
                dispatcher.stop();

            dispatcher = null;
        }
    }
}
