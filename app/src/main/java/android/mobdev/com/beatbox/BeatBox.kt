package android.mobdev.com.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException


class BeatBox {

    private val TAG = "BeatBox"
    private val SOUNDS_FOLDER = "sample_sounds"
    private var mAssets: AssetManager? = null
    private val mSounds = ArrayList<Sound>()

    constructor(context: Context) {
        mAssets = context.assets
        loadSounds()
    }

    private fun loadSounds() {
        val soundNames: Array<String>
        try {
            soundNames = mAssets?.list(SOUNDS_FOLDER) as Array<String>
            Log.i(TAG, "Found " + soundNames.size + " sounds");
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }
        for (filename in soundNames) {
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            mSounds.add(sound)
        }
    }

    fun getSounds(): ArrayList<Sound> {
        return mSounds
    }
}