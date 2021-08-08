package ph.com.panahon

open class Date (month: Int, day: Int, year: Int) {

    private var recordedMonth = month
    private var recordedDay = day
    private var recordedYear = year

    open fun getMonthString() : String {
        return when (recordedMonth) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sept"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> "The End of Time"
        }
    }

    open fun getDate(): String {
        return getMonthString() + ". " + recordedDay + ", " + recordedYear
    }
}