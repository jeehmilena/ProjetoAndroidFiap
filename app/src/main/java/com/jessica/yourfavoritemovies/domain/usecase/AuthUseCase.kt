package com.jessica.yourfavoritemovies.domain.usecase

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.jessica.yourfavoritemovies.authentication.view.LoginActivity

class AuthUseCase {

    fun logout(context: Context) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {

            }
    }
}