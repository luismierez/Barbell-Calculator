package luismierez.barbellcalculator

import io.reactivex.Observable
import luismierez.barbellcalculator.model.PlatePair

/**
 * Created by luismierez on 5/9/17.
 */
interface MainContract {
    interface View {
        fun displayTotalLiftWeight(liftWeight: String)
        fun displayPlatesNeeded(plates: List<Int>)
    }

    interface Presenter {
        fun addPlatePair(platePair: PlatePair)
        fun removePlatePair(platePair: PlatePair)
        fun registerWeight(weightObservable: Observable<String>)
    }
}