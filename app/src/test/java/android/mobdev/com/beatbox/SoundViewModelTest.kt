package android.mobdev.com.beatbox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {
    private var mBeatBox: BeatBox? = null
    private var mSound: Sound? = null
    private var mSubject: SoundViewModel? = null

    @Before
    fun setUp() {
        mBeatBox = mock(BeatBox::class.java)
        mSound = Sound("assetPath")
        mSubject = mBeatBox?.let { SoundViewModel(it) }
        mSubject?.setSound(mSound ?: Sound("assetPath"))
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(mSubject?.getTitle(), `is`(mSound?.getName()))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        mSubject?.onButtonClicked()
        verify(mBeatBox)?.play(mSound ?: Sound("assetPath"))
    }
}