package me.yusufeka.kadeyusuf.responses

import me.yusufeka.kadeyusuf.models.Event

data class MatchResponse(

    // for last and next event
    val events: List<Event>?,

    // for search event
    val event: List<Event>?
)