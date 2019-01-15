package android.mobdev.com.beatbox

import android.databinding.BaseObservable
import android.databinding.Bindable

class SoundViewModel : BaseObservable {
    private var mSound: Sound? = null
    private var mBeatBox: BeatBox? = null

    constructor(beatBox: BeatBox) {
        mBeatBox = beatBox
    }

    fun getSound(): Sound? {
        return mSound
    }

    fun setSound(sound: Sound) {
        mSound = sound
        notifyChange()
    }

    @Bindable
    fun getTitle(): String {
        return mSound?.getName().toString()
    }
}