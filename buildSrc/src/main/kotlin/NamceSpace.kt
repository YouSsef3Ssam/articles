object NamceSpace {

    const val applicationId = "com.youssef.articles"

    object Core {
        private const val core = "com.youssef.core"
        const val network = "$core.network"
    }

    object Common {
        private const val common = "com.youssef.common"
        const val utils = "$common.utils"
        const val ui = "$common.ui"
    }

    object Feature {
        private const val feature = "com.youssef.feature"
        const val articles = "$feature.articles"
    }
}