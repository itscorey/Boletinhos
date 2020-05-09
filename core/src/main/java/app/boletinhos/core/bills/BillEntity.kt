package app.boletinhos.core.bills

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.boletinhos.domain.BillStatus
import java.time.LocalDate

@Entity(tableName = "bills")
data class BillEntity(
    val name: String,
    val description: String,
    val value: Long,
    val paymentDate: LocalDate?,
    val dueDate: LocalDate,
    val status: BillStatus
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}