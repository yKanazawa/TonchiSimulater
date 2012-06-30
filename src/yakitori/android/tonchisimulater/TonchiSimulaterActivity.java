package yakitori.android.tonchisimulater;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.media.MediaPlayer;
import yakitori.android.tonchisimulater.R;

public class TonchiSimulaterActivity extends Activity
implements MediaPlayer.OnCompletionListener
{
    public static final Context TonchiSimulaterActivity = null;
    /** Called when the activity is first created. */
    Button button1 = null;
    private MediaPlayer mediaPlayerA;
    private MediaPlayer mediaPlayerB;
    private boolean isPlaying;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mediaPlayerA = MediaPlayer.create(this, R.raw.a);
        mediaPlayerB = MediaPlayer.create(this, R.raw.b);
        mediaPlayerA.setOnCompletionListener(this);
        isPlaying = false;
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new ButtonClickListener());
    }

    class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            if(!isPlaying) {
                mediaPlayerA.start();
//                mediaPlayerA.setLooping(true);
                isPlaying = true;
            } else {
                isPlaying = false;
                button1.setEnabled(false);
//                mediaPlayerA.setLooping(false);
            }
        }

    }

    // �T�E���h�Đ��I�����ɌĂ΂��
    public void onCompletion(MediaPlayer mp) {
        if(isPlaying) {
            button1.setText("�I��");
            mediaPlayerA.start();
        } else {
            mediaPlayerB.start();
            button1.setText("�J�n");
            button1.setEnabled(true);
        }
    }

    // �A�N�e�B�r�e�B���o�b�N�O���E���h�ɂȂ钼�O�ɌĂяo�����
    protected void onPause() {
        super.onPause();
        mediaPlayerA.stop();
        mediaPlayerA.reset();
        mediaPlayerA.release();
        mediaPlayerB.stop();
        mediaPlayerB.reset();
        mediaPlayerB.release();
        this.finish();
    }
}