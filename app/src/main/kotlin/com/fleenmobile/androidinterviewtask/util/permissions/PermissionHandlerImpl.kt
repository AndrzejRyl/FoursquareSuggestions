package com.fleenmobile.androidinterviewtask.util.permissions

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PermissionHandlerImpl(
        private val activity: Activity,
        private val publishSubject: PublishSubject<Boolean>
) : PermissionHandler {

    companion object {
        const val PERMISSIONS_REQUEST_CODE = 189
    }

    override fun request(vararg permissions: String): Observable<Boolean> {
        val permissionsGranted = permissions
                .map { permission -> ContextCompat.checkSelfPermission(activity, permission) }
                .map { grantResult -> grantResult == PackageManager.PERMISSION_GRANTED }
                .fold(true, { acc, newValue -> acc && newValue })

        if (!permissionsGranted) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_REQUEST_CODE)
        }

        publishSubject.onNext(permissionsGranted)

        return publishSubject
    }
}