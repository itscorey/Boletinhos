package app.boletinhos.storage.bills

import app.boletinhos.storage.factory.BillsFactory
import app.boletinhos.storage.testutil.AppDatabaseTest
import app.boletinhos.domain.bill.BillStatus.OVERDUE
import app.boletinhos.domain.bill.BillStatus.PAID
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.time.LocalDate

class BillsDaoTest : AppDatabaseTest() {
    @Test fun `should get bills by its status`() = runBlockingTest {
        val expected = BillsFactory.paids

        (expected + BillsFactory.overdue)
            .forEach { manageBillDao.create(it) }

        billsDao.getByStatus(PAID).test { actual ->
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test fun `should update a given bill`() = runBlockingTest {
        val bill = BillsFactory
            .pick()
            .first()
            .copy(dueDate = LocalDate.now().minusMonths(1), status = OVERDUE)

        manageBillDao.create(bill)

        val updated = billsDao.getById(id = 1)
            .copy(description = "My new description", paymentDate = LocalDate.now())
            .also { it.id = 1 }

        manageBillDao.pay(updated)

        assertThat(billsDao.getById(id = 1)).isEqualTo(updated)
    }
}