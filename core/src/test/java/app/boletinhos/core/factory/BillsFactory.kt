package app.boletinhos.core.factory

import app.boletinhos.core.bills.Bill
import app.boletinhos.core.bills.BillStatus
import org.threeten.bp.LocalDate
import org.threeten.bp.Month

object BillsFactory {
    val unpaid = Bill(
        name = "Unpaid bill",
        description = "unpaid",
        value = 200_00,
        paymentDate = null,
        dueDate = LocalDate.of(2020, Month.DECEMBER, 23),
        status = BillStatus.UNPAID
    )

    val paid = Bill(
        name = "paid bill",
        description = "paid",
        value = 200_00,
        paymentDate = LocalDate.of(2020, Month.DECEMBER, 20),
        dueDate = LocalDate.of(2020, Month.DECEMBER, 23),
        status = BillStatus.PAID
    )

    val overdue = Bill(
        name = "overdue bill",
        description = "overdue",
        value = 200_00,
        paymentDate = null,
        dueDate = LocalDate.of(2020, Month.DECEMBER, 1),
        status = BillStatus.OVERDUE
    )

    val merged = listOf(
        unpaid,
        unpaid.copy(name = "New bill"),
        unpaid.copy(name = "Another bill"),
        paid,
        paid.copy(name = "2nd paid bill"),
        overdue
    )
}