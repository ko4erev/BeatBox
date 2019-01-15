package android.mobdev.com.beatbox


import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil.inflate
import android.mobdev.com.beatbox.databinding.FragmentBeatBoxBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.mobdev.com.beatbox.databinding.ListItemSoundBinding
import android.databinding.DataBindingUtil


class BeatBoxFragment : Fragment() {

    private var mBeatBox: BeatBox? = null

    companion object {
        fun newInstance(): BeatBoxFragment {
            return BeatBoxFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBeatBox = activity?.let { BeatBox(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: FragmentBeatBoxBinding = inflate(inflater, R.layout.fragment_beat_box, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = mBeatBox?.let { SoundAdapter(it.getSounds()) }
        return binding.root
    }

    private inner class SoundHolder(private val mBinding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        init {
            mBinding.root
            mBinding.viewModel = mBeatBox?.let { SoundViewModel(it) }
        }

        fun bind(sound: Sound) {
            mBinding.viewModel?.setSound(sound)
            mBinding.executePendingBindings()
        }
    }

    private inner class SoundAdapter : RecyclerView.Adapter<SoundHolder> {
        private var mSounds = ArrayList<Sound>()

        constructor(sounds: ArrayList<Sound>) {
            mSounds = sounds
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val inflater = LayoutInflater.from(activity)
            val binding = DataBindingUtil
                .inflate<ListItemSoundBinding>(inflater, R.layout.list_item_sound, parent, false)
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            var sound = mSounds[position]
            holder.bind(sound)
        }

        override fun getItemCount(): Int {
            return mSounds?.size
        }
    }
}