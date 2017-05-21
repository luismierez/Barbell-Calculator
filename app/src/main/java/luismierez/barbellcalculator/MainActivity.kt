package luismierez.barbellcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import luismierez.barbellcalculator.model.PlatePair

class MainActivity : AppCompatActivity(), MainContract.View {

    val presenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addHalf5.setOnClickListener {
            presenter.addPlatePair(PlatePair(2.5))
        }

        add5.setOnClickListener {
            presenter.addPlatePair(PlatePair(5.0))
        }

        add10.setOnClickListener {
            presenter.addPlatePair(PlatePair(10.0))
        }

        add25.setOnClickListener {
            presenter.addPlatePair(PlatePair(25.0))
        }

        add35.setOnClickListener {
            presenter.addPlatePair(PlatePair(35.0))
        }

        add45.setOnClickListener {
            presenter.addPlatePair(PlatePair(45.0))
        }

        removeHalf5.setOnClickListener {
            presenter.removePlatePair(PlatePair(2.5))
        }

        remove5.setOnClickListener {
            presenter.removePlatePair(PlatePair(5.0))
        }

        remove10.setOnClickListener {
            presenter.removePlatePair(PlatePair(10.0))
        }

        remove25.setOnClickListener {
            presenter.removePlatePair(PlatePair(25.0))
        }

        remove35.setOnClickListener {
            presenter.removePlatePair(PlatePair(35.0))
        }

        remove45.setOnClickListener {
            presenter.removePlatePair(PlatePair(45.0))
        }

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
