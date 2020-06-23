package app.boletinhos.main

import android.app.Application
import app.boletinhos.crashcat.CrashCat
import app.boletinhos.injection.app.AppComponent
import app.boletinhos.injection.app.DaggerAppComponent
import app.boletinhos.injection.context.AppContextComponent
import app.boletinhos.injection.context.DaggerAppContextComponent
import app.boletinhos.injection.crashlytics.CrashlyticsComponent
import app.boletinhos.injection.crashlytics.DaggerCrashlyticsComponent
import javax.inject.Inject

class MainApplication : Application() {
    private lateinit var component: AppComponent

    private fun contextComponent(): AppContextComponent {
        return DaggerAppContextComponent.factory()
            .create(this)
    }

    private fun crashlyticsComponent(): CrashlyticsComponent {
        return DaggerCrashlyticsComponent.factory().create()
    }

    private fun injector(): AppComponent {
        return DaggerAppComponent.factory()
            .create(contextComponent(), crashlyticsComponent())
            .also { component -> component.inject(this) }
    }

    @Inject lateinit var crashCat: CrashCat

    override fun onCreate() {
        super.onCreate()
        component = injector()
        crashCat.configure()
    }

    fun mainComponent() = component
}
