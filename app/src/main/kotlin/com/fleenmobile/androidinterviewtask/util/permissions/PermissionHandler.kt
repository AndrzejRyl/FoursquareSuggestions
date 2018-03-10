package com.fleenmobile.androidinterviewtask.util.permissions

import io.reactivex.Observable

interface PermissionHandler {
    fun request(vararg permissions: String): Observable<Boolean>
}