package luismierez.barbellcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    val presenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.registerWeight(RxTextView.afterTextChangeEvents(totalWeight)
                .observeOn(AndroidSchedulers.mainThread())
                .map { txChangeEvent -> txChangeEvent.editable().toString() })

    }

    override fun displayTotalLiftWeight(liftWeight: String) {
        totalWeight.setText(liftWeight)
    }

    override fun displayPlatesNeeded(plates: List<Int>) {
        num45Plates.text = plates[0].toString()
        num35Plates.text = plates[1].toString()
        num25Plates.text = plates[2].toString()
        num10Plates.text = plates[3].toString()
        num5Plates.text = plates[4].toString()
        numHalf5Plates.text = plates[5].toString()

    }
}
