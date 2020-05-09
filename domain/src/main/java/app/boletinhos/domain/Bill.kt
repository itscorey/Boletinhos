package app.boletinhos.domain

import java.time.LocalDate

data class Bill(
    val id: Int,
    val name: String,
    val description: String,
    val value: Long,
    val paymentDate: LocalDate?,
    val dueDate: LocalDate,
    val status: BillStatus
)