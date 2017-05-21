package luismierez.barbellcalculator

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import luismierez.barbellcalculator.model.PlatePair

/**
 * Created by luismierez on 5/9/17.
 */
class MainPresenter(var view: MainContract.View): MainContract.Presenter {

    val platePairs = ArrayList<PlatePair>()
    var barWeight = 45.0
    val disposables = CompositeDisposable()

    init {
        view.displayTotalLiftWeight(barWeight.toString())
    }

    override fun addPlatePair(platePair: PlatePair) {
        platePairs.add(platePair)
        calculateLiftWeight()
    }

    override fun removePlatePair(platePair: PlatePair) {
        platePairs.remove(platePair)
        calculateLiftWeight()
    }

    override fun registerWeight(weightObservable: Observable<String>) {
        disposables.add(weightObservable
                .map { weightString -> if (weightString.isEmpty()) 0.0 else weightString.toDouble()}
                .subscribe{ weightToLift ->
                    val pairs = calculatePlatePairs(weightToLift)
                    view.displayPlatesNeeded(pairs)
                })
    }

    fun calculateLiftWeight() {
        var totalPlateWeight = platePairs.sumByDouble { it.calculateLiftedWeight() }
        val totalLiftWeight = totalPlateWeight + barWeight
        view.displayTotalLiftWeight(totalLiftWeight.toString())
    }

    fun calculatePlatePairs(liftWeight: Double): List<Int> {
        val plateTypes = arrayOf(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)
        var weight = liftWeight - barWeight
        weight /= 2

        val result = mutableListOf(0, 0, 0, 0, 0, 0)

        for (i in plateTypes.indices) {
            while (weight >= plateTypes[i]) {
                result[i] += 1
                weight -= plateTypes[i]
            }
        }

        return result.toList()
    }
}