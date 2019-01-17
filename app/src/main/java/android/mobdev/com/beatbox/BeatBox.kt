package android.mobdev.com.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.media.AudioManager
import android.util.Log
import java.io.IOException
import android.media.SoundPool


open class BeatBox {

    private val TAG = "BeatBox"
    private val SOUNDS_FOLDER = "sample_sounds"
    private val MAX_SOUNDS = 5
    private var mAssets: AssetManager? = null
    private val mSounds = ArrayList<Sound>()
    private var mSoundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)

    constructor(context: Context) {
        mAssets = context.assets
        loadSounds()
        print("")
    }

    fun play(sound: Sound) {
        val soundId = sound.getSoundId() ?: return
        mSoundPool?.play(soundId, 1.0F, 1.0F, 1, 0, 1.0F)
        print("")
    }

    fun release() {
        mSoundPool?.release()
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
            try {
                val assetPath = "$SOUNDS_FOLDER/$filename"
                val sound = Sound(assetPath)
                load(sound)
                mSounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
    }

    private fun load(sound: Sound) {
        val afd = mAssets?.openFd(sound.getAssetPath())
        val soundId = mSoundPool?.load(afd, 1)
        soundId?.let { sound.setSoundId(it) }
    }

    fun getSounds(): ArrayList<Sound> {
        return mSounds
    }
}