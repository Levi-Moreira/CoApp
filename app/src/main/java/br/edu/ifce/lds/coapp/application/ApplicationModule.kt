package br.edu.ifce.lds.coapp.application

import android.app.Application
import android.content.Context
import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lds on 7/13/17.
 */
@Module class ApplicationModule(val app: Application) {

    @Provides @Singleton fun providesContext(): Context = app

    @Provides @Singleton fun providesApplication(): Application = app

    @Provides @Singleton fun providesPreferences(): PreferencesUtil = PreferencesUtil(app)

    @Provides @Singleton fun providedsBackend(): CoAppBackend = CoAppBackend()

}