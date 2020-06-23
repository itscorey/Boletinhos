package app.boletinhos.injection.app

import android.app.Application
import android.content.Context

@dagger.Module object AppModule {
    @dagger.Provides internal fun provideContext(app: Application): Context = app
}