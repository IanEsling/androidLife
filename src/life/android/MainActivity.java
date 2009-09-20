package life.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // tell system to use the layout defined in our XML file
        setContentView(R.layout.life);

        // get handles to the LunarView from XML, and its LunarThread
//        mLunarView = (LunarView) findViewById(R.id.lunar);
//        mLunarThread = mLunarView.getThread();

        // give the LunarView a handle to the TextView used for messages
//        mLunarView.setTextView((TextView) findViewById(R.id.text));

//        if (savedInstanceState == null)
//        {
            // we were just launched: set up a new game
//            mLunarThread.setState(LunarThread.STATE_READY);
//            Log.w(this.getClass().getName(), "SIS is null");
//        } else
//        {
            // we are being restored: resume a previous game
//            mLunarThread.restoreState(savedInstanceState);
//            Log.w(this.getClass().getName(), "SIS is nonnull");
//        }
    }
}
