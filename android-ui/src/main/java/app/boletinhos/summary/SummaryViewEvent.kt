package app.boletinhos.summary

sealed class SummaryViewEvent {
    object FetchData : SummaryViewEvent()
    object OnClickInAddBill : SummaryViewEvent()
}
