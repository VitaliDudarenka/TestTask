package com.gmail.dudarenka.vitali.testtask.presentation.inject

import android.content.Context
import com.gmail.dudarenka.vitali.data.net.RestService
import com.gmail.dudarenka.vitali.data.repositories.UserRepositoryImpl
import com.gmail.dudarenka.vitali.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.domain.repositories.UserRepository
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
    fun provideUserRepository(restService: RestService): UserRepository = UserRepositoryImpl(restService)

    @Provides
    fun provideRestService(@Named(URL_INJECT_NAME_DEBUG) serverUrl: String): RestService = RestService(serverUrl)

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    @Provides
    @Named(URL_INJECT_NAME_DEBUG)
    fun provideServerUrlDebug(): String = "https://api.github.com/"

    @Provides
    @Named(URL_INJECT_NAME_RELEASE)
    fun provideServerUrlRelease(): String = "https://api.github.com/"


}