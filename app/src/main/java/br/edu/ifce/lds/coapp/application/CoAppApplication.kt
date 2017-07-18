package br.edu.ifce.lds.coapp.application

/**
 * Created by ellca on 30/05/2017.
 */

import android.content.Context
import br.edu.ifce.lds.coapp.R
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Acra reporter, sends a report of a crash in the application to the URI provided
 * In this project we use TraceBot to keep track of crashes in the application
 */
@ReportsCrashes(mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text, formUri = "") //mailTo = "levi.m.albuquerque@gmail.com",
class CoAppApplication : com.orm.SugarApp() {

    //start up the dagger for the application
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()

        //apply custom font to the whole app
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("font/DroidSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    companion object get {
        fun getApplication(context: Context): CoAppApplication = context.applicationContext as CoAppApplication
    }
}

