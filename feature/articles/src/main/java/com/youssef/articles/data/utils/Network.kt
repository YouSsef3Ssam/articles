package com.youssef.articles.data.utils

import com.youssef.articles.data.utils.Network.Path.PERIOD

object Network {

    object EndPoints {
        const val ARTICLES = "viewed/{$PERIOD}.json"
    }

    object Path {
        const val PERIOD = "period"
    }
}
