package br.edu.ifce.lds.coapp.application

/**
 * Created by ellca on 30/05/2017.
 */

import android.app.Application
import br.edu.ifce.lds.coapp.R
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Acra reporter, sends a report of a crash in the application to the URI provided
 * In this project we use TraceBot to keep track of crashes in the application
 */
@ReportsCrashes(mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text, formUri = "") //mailTo = "levi.m.albuquerque@gmail.com",

class CoAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("font/DroidSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
