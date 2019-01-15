package android.mobdev.com.beatbox

class Sound {

    private var mAssetPath: String? = null
    private var mName: String? = null

    constructor(assetPath: String) {
        mAssetPath = assetPath
        val components = assetPath.split("/")
        val filename = components[components.size - 1]
        mName = filename.replace(".wav", "")
    }

    fun getAssetPath(): String? {
        return mAssetPath
    }

    fun getName(): String? {
        return mName
    }
}