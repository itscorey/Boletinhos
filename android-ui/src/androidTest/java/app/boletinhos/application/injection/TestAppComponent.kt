package app.boletinhos.application.injection

import android.app.Application
import app.boletinhos.bill.add.AddBillViewTest
import app.boletinhos.bill.injection.BillServiceModule
import app.boletinhos.rule.UsesDatabaseRule
import app.boletinhos.summary.SummaryViewTest
import app.boletinhos.summary.injection.SummaryServiceModule
import app.boletinhos.welcome.WelcomeViewTest
import common.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [
    TestModule::class,
    TestDatabaseModule::class,
    SummaryServiceModule::class,
    BillServiceModule::class
])
interface TestAppComponent : AppComponent {
    fun inject(test: SummaryViewTest)
    fun inject(rule: UsesDatabaseRule)
    fun inject(test: WelcomeViewTest)
    fun inject(test: AddBillViewTest)

    @Component.Factory interface Factory {
        fun create(@BindsInstance app: Application): TestAppComponent
    }
}
