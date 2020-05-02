package app.boletinhos.core.bills

import app.boletinhos.core.factory.BillsFactory
import app.boletinhos.core.testutil.AppDatabaseTest
import app.boletinhos.core.testutil.runBlocking
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class BillsDaoTest : AppDatabaseTest() {
    @Test fun `should insert and get inserted bill`() = mainCoroutineRule.runBlocking { scope ->
        // @given a unpaid bill
        val expected = BillsFactory.unpaid

        // @when inserting it to the app database
        billsDao.insert(expected)

        // @then it should be added in the database
        val job = scope.launch {
            billsDao.getAll().take(1).collect { bills ->
                assertThat(bills.first()).isEqualTo(expected)
            }
        }

        job.cancelAndJoin()
    }

    @Test fun `should get bills by its status`() = runBlockingTest {
        // @given a list of bills
        val merged = BillsFactory.merged
        val expected = merged.filter { bill -> bill.status == BillStatus.UNPAID }

        // @and they are inserted in the database
        merged.forEach { bill ->
            billsDao.insert(bill)
        }

        // @when fetching bills by `unpaid` status
        val actual = billsDao.getByStatus(BillStatus.UNPAID)

        // @then the list of fetched bills should be equal to the expected
        assertThat(actual).isEqualTo(expected)
    }
}