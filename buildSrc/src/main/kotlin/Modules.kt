object Modules {

    const val app = ":app"

    object Core {
        private const val core = ":core"
        const val network = "$core:network"
    }

    object Commons {
        private const val common = ":common"
        const val utils = "$common:utils"
        const val ui = "$common:ui"
        const val uiTest = "$common:uiTest"
    }

    object Features {
        private const val feature = ":feature"
        const val articles = "$feature:articles"
    }

}