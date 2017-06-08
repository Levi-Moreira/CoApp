package br.edu.ifce.lds.coapp.landing.views


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R.layout.fragment_landing
import kotlinx.android.synthetic.main.fragment_landing.*
import org.jetbrains.anko.onClick


/**
 * Shows the first fragment with the initial menu
 */
class LandingFragment : Fragment() {


    lateinit var mLandingCallbacks: ILandingCallbacks

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(fragment_landing, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //after the views were inflated, set up views reactions
        setUpViews()
    }

    /**
     * Some view reactions and button clicks
     */
    fun setUpViews() {

        buttonPlans.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.meet_ou_plans, android.widget.Toast.LENGTH_SHORT).show()
        }

        buttonEmergencySchedule.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.emergency_sheducling, android.widget.Toast.LENGTH_SHORT).show()
        }

        buttonContact.onClick {
            mLandingCallbacks.startContactActivity()
        }

        buttonJoin.onClick {
            android.widget.Toast.makeText(this.context, br.edu.ifce.lds.coapp.R.string.sign_up, android.widget.Toast.LENGTH_SHORT).show()
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mLandingCallbacks = context as ILandingCallbacks

        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    interface ILandingCallbacks {
        fun startContactActivity()
    }

}// Required empty public constructor
