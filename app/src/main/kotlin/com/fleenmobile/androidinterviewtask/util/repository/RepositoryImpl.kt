package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.data.HereNowData
import com.fleenmobile.androidinterviewtask.data.PhotoData
import com.fleenmobile.androidinterviewtask.data.PhotoItem
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.data.VenueLocation
import io.reactivex.Observable

class RepositoryImpl : Repository {

    override fun fetchVenues(location: String): Observable<List<Venue>> {
        // todo replace with real logic

        return Observable.just(
                listOf(
                        Venue(
                                "venue1",
                                "Venue1",
                                VenueLocation("Address 1"),
                                HereNowData("23 people here"),
                                PhotoData(1,
                                        listOf(
                                                PhotoItem("https://igx.4sqi.net/img/general/",
                                                        "/1022386_wEd7VXnWzp5lajvRLtAPunoiufDSIq8PMEtEuIH2Tzg.jpg")))),
                        Venue(
                                "venue2",
                                "Venue2",
                                VenueLocation("Address 2"),
                                HereNowData("23 people here"),
                                PhotoData(1,
                                        listOf(
                                                PhotoItem("https://igx.4sqi.net/img/general/",
                                                        "/1022386_wEd7VXnWzp5lajvRLtAPunoiufDSIq8PMEtEuIH2Tzg.jpg")))),
                        Venue(
                                "venue3",
                                "Venue3",
                                VenueLocation("Address 3sdfsfhgfhdfghjfgdhfg"),
                                HereNowData("23 people here"),
                                PhotoData(3,
                                        listOf(
                                                PhotoItem("https://igx.4sqi.net/img/general/",
                                                        "/1022386_wEd7VXnWzp5lajvRLtAPunoiufDSIq8PMEtEuIH2Tzg.jpg"),
                                                PhotoItem("https://igx.4sqi.net/img/general/",
                                                        "/1022386_wEd7VXnWzp5lajvRLtAPunoiufDSIq8PMEtEuIH2Tzg.jpg"),
                                                PhotoItem("https://igx.4sqi.net/img/general/",
                                                        "/1022386_wEd7VXnWzp5lajvRLtAPunoiufDSIq8PMEtEuIH2Tzg.jpg"))))
                )
        )
    }
}