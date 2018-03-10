package com.fleenmobile.androidinterviewtask.data

data class VenuesResponseItem(val venue: Venue)

data class VenuesResponseGroup(val type: String, val name: String, val items: List<VenuesResponseItem>)

data class VenuesResponseData(val groups: List<VenuesResponseGroup>)

data class VenuesResponse(private val response: VenuesResponseData) {
    fun getVenues(): List<Venue> {
        val groups = response.groups
        val recommendedGroup = groups
                .filter { it.name == "recommended" }
                .firstOrNull()

        recommendedGroup?.let {
            return it.items
                    .map { it.venue }
                    .toList()
        }

        return listOf()
    }
}