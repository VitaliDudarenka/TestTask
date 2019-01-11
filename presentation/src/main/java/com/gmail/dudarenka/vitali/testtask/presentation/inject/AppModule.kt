package com.gmail.dudarenka.vitali.testtask.presentation.inject

import android.content.Context
import com.gmail.dudarenka.vitali.testtask.presentation.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }


    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    @Provides
    @Named(URL_INJECT_NAME_DEBUG)
    fun provideServerUrlDebug(): String = ""

    @Provides
    @Named(URL_INJECT_NAME_RELEASE)
    fun provideServerUrlRelease(): String = ""


}