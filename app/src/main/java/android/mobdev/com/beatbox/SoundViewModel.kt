package android.mobdev.com.beatbox

import android.databinding.BaseObservable
import android.databinding.BindingAdapter

class SoundViewModel : BaseObservable {
    private var mSound: Sound? = null
    private var mBeatBox: BeatBox? = null

    constructor(beatBox: BeatBox) {
        mBeatBox = beatBox
    }

    @BindingAdapter("android:title")
    fun getTitle(): String {
        return mSound?.getName().toString()
    }

    fun getSound(): Sound? {
        return mSound
    }

    fun setSound(sound: Sound) {
        mSound = sound
        notifyChange()
    }

    fun onButtonClicked() {
        mSound?.let { mBeatBox?.play(it) }
    }
}