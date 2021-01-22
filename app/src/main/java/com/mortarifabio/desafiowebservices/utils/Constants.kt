package com.mortarifabio.desafiowebservices.utils

class Constants {
    object Api {
        const val API_BASE_URL = "https://gateway.marvel.com:443/v1/public/"
        const val API_TIMESTAMP_LABEL = "ts"
        const val API_KEY_LABEL = "apikey"
        const val API_HASH_LABEL = "hash"
        const val API_ORDER_LABEL = "orderBy"
        const val API_ORDER = "-focDate"
        const val API_LIMIT = 50
        const val API_FIRST_PAGE = 1
        const val CHARACTER_ID = 1009610 //Spider-Man
    }

    object Intent {
        const val INTENT_KEY_COMIC = "comic_object"
    }

    object Authentication {
        const val SHARED_PREFERENCES_FILENAME = "com.mortarifabio.desafiowebservices.sharedpreferences"
        const val SHARED_PREFERENCES_EMAIL_KEY = "user_email"
    }
}