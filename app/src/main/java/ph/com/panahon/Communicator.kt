package ph.com.panahon

/**
 * An Interface. Basically this Interface is what makes communication between and among Fragments possible.
 * This Interface is implemented by the MainActivity; that means, all data is goes through and is stored in
 * MainActivity; After that, Fragments now retrieve data from MainActivity since everyone is rooted in
 * MainActivity.
 */
interface Communicator {
    fun storeUnitDegreePreference(unitDegreeCode: Int)
}